(function() {
    // СОЗДАНИЕ АУДИО-ФАЙЛОВ ДИНАМИЧЕСКИ (через Web Audio + осцилляторы с возобновлением контекста)
    let audioContext = null;
    
    function getAudioContext() {
        if (!audioContext) {
            audioContext = new (window.AudioContext || window.webkitAudioContext)();
        }
        return audioContext;
    }
    
    function playSound(type) {
        // Возобновляем контекст, если он в suspended состоянии (политика браузера)
        const ctx = getAudioContext();
        if (ctx.state === 'suspended') {
            ctx.resume().then(() => {
                actuallyPlaySound(type, ctx);
            });
        } else {
            actuallyPlaySound(type, ctx);
        }
    }
    
    function actuallyPlaySound(type, ctx) {
        const now = ctx.currentTime;
        const oscillator = ctx.createOscillator();
        const gainNode = ctx.createGain();
        
        oscillator.connect(gainNode);
        gainNode.connect(ctx.destination);
        
        let frequency = 440;
        let duration = 0.35;
        
        switch(type) {
            case 'Kick':
                frequency = 80;
                duration = 0.45;
                oscillator.type = 'sine';
                break;
            case 'Snare':
                frequency = 180;
                duration = 0.28;
                oscillator.type = 'triangle';
                break;
            case 'Hi-Hat':
                frequency = 8000;
                duration = 0.12;
                oscillator.type = 'square';
                break;
            case 'Tom Low':
                frequency = 130;
                duration = 0.38;
                oscillator.type = 'sine';
                break;
            case 'Tom Mid':
                frequency = 200;
                duration = 0.35;
                oscillator.type = 'sine';
                break;
            case 'Tom High':
                frequency = 290;
                duration = 0.32;
                oscillator.type = 'sine';
                break;
            case 'Crash':
                frequency = 2400;
                duration = 0.9;
                oscillator.type = 'triangle';
                break;
            default:
                frequency = 440;
        }
        
        oscillator.frequency.value = frequency;
        gainNode.gain.value = 0.25;
        
        oscillator.start();
        gainNode.gain.exponentialRampToValueAtTime(0.00001, now + duration);
        oscillator.stop(now + duration);
    }
    
    // Инициализация инструментов
    const instruments = [
        { name: 'Kick Drum', defaultKey: 'A', sound: 'Kick' },
        { name: 'Snare', defaultKey: 'S', sound: 'Snare' },
        { name: 'Hi-Hat', defaultKey: 'D', sound: 'Hi-Hat' },
        { name: 'Tom Low', defaultKey: 'F', sound: 'Tom Low' },
        { name: 'Tom Mid', defaultKey: 'G', sound: 'Tom Mid' },
        { name: 'Tom High', defaultKey: 'H', sound: 'Tom High' },
        { name: 'Crash Cymbal', defaultKey: 'J', sound: 'Crash' }
    ];

    let pads = [];
    let keyMap = new Map();
    let isPlayingSequence = false;
    let currentEditIndex = null;
    let audioUnlocked = false;
    
    // Разблокировка аудио (первое взаимодействие с любым элементом)
    function unlockAudio() {
        if (audioUnlocked) return;
        const ctx = getAudioContext();
        if (ctx.state === 'suspended') {
            ctx.resume().then(() => {
                audioUnlocked = true;
                updateStatus('🔊 Audio ready! Play some drums!');
            });
        } else {
            audioUnlocked = true;
        }
    }
    
    function buildUI() {
        const container = document.createElement('div');
        container.className = 'drum-kit';
        
        const title = document.createElement('h1');
        title.textContent = '🥁 VIRTUAL DRUM KIT 🥁';
        const subtitle = document.createElement('div');
        subtitle.className = 'subtitle';
        subtitle.textContent = 'Click or press keys • Customize key bindings • Sequence player';
        container.appendChild(title);
        container.appendChild(subtitle);
        
        const padsGrid = document.createElement('div');
        padsGrid.className = 'drum-pads';
        
        instruments.forEach((inst, idx) => {
            const pad = document.createElement('div');
            pad.className = 'drum-pad';
            pad.setAttribute('data-index', idx);
            
            const nameSpan = document.createElement('div');
            nameSpan.className = 'drum-name';
            nameSpan.textContent = inst.name;
            
            const keyDiv = document.createElement('div');
            keyDiv.className = 'key-badge';
            const keySpan = document.createElement('span');
            keySpan.className = 'key-label';
            keySpan.id = `key-label-${idx}`;
            keySpan.textContent = inst.defaultKey;
            const editBtn = document.createElement('button');
            editBtn.className = 'edit-btn';
            editBtn.textContent = '✏️ Edit';
            editBtn.addEventListener('click', (e) => {
                e.stopPropagation();
                openEditModal(idx);
            });
            keyDiv.appendChild(keySpan);
            keyDiv.appendChild(editBtn);
            
            pad.appendChild(nameSpan);
            pad.appendChild(keyDiv);
            
            // Клик по паду
            pad.addEventListener('click', (e) => {
                e.stopPropagation();
                unlockAudio();
                playSoundByIdx(idx, true);
            });
            
            padsGrid.appendChild(pad);
            pads.push(pad);
            keyMap.set(inst.defaultKey, idx);
        });
        
        container.appendChild(padsGrid);
        
        // Секция последовательности
        const seqSection = document.createElement('div');
        seqSection.className = 'sequence-section';
        
        const seqTitle = document.createElement('div');
        seqTitle.className = 'sequence-title';
        seqTitle.textContent = '🎵 SEQUENCE PLAYER 🎵';
        
        const seqControls = document.createElement('div');
        seqControls.className = 'sequence-controls';
        
        const seqInput = document.createElement('input');
        seqInput.type = 'text';
        seqInput.placeholder = 'Enter keys (e.g., ASDFGHJ)';
        seqInput.className = 'sequence-input';
        seqInput.id = 'sequenceInput';
        seqInput.maxLength = instruments.length * 2;
        
        const playSeqBtn = document.createElement('button');
        playSeqBtn.textContent = '▶ PLAY SEQUENCE';
        playSeqBtn.className = 'play-btn';
        playSeqBtn.id = 'playSeqBtn';
        
        seqControls.appendChild(seqInput);
        seqControls.appendChild(playSeqBtn);
        
        const statusMsg = document.createElement('div');
        statusMsg.className = 'status-message';
        statusMsg.id = 'statusMsg';
        statusMsg.textContent = '🎧 Click any pad or press a key to unlock audio!';
        
        seqSection.appendChild(seqTitle);
        seqSection.appendChild(seqControls);
        seqSection.appendChild(statusMsg);
        container.appendChild(seqSection);
        
        document.body.appendChild(container);
        
        // Обработчики
        playSeqBtn.addEventListener('click', () => {
            unlockAudio();
            startSequence();
        });
        
        seqInput.addEventListener('input', (e) => {
            let val = e.target.value.toUpperCase();
            let filtered = '';
            for(let ch of val) {
                if(keyMap.has(ch) && filtered.length < instruments.length * 2) filtered += ch;
            }
            seqInput.value = filtered;
        });
        
        // Глобальный клик для разблокировки аудио (на любое место)
        document.body.addEventListener('click', unlockAudio, { once: false });
        document.body.addEventListener('keydown', (e) => {
            if (e.key.length === 1 && /[A-Za-z]/i.test(e.key)) {
                unlockAudio();
            }
        });
    }
    
    function openEditModal(index) {
        if(isPlayingSequence) return;
        currentEditIndex = index;
        const currentKey = getKeyForIndex(index);
        
        const overlay = document.createElement('div');
        overlay.className = 'edit-overlay';
        const modal = document.createElement('div');
        modal.className = 'edit-modal';
        
        const title = document.createElement('h3');
        title.textContent = `Edit key for ${instruments[index].name}`;
        const input = document.createElement('input');
        input.type = 'text';
        input.placeholder = 'New key (A-Z)';
        input.maxLength = 1;
        input.value = currentKey;
        
        const saveBtn = document.createElement('button');
        saveBtn.textContent = 'Save (Enter)';
        
        modal.appendChild(title);
        modal.appendChild(input);
        modal.appendChild(saveBtn);
        overlay.appendChild(modal);
        document.body.appendChild(overlay);
        
        const closeModal = () => overlay.remove();
        
        const saveKey = () => {
            let newKey = input.value.trim().toUpperCase();
            if(newKey.length === 0 || !/[A-Z]/i.test(newKey)) {
                alert('Please enter a valid letter (A-Z)');
                return;
            }
            // проверка уникальности
            let isUnique = true;
            let conflictingIndex = -1;
            for(let [k, idx] of keyMap.entries()) {
                if(k === newKey && idx !== index) {
                    isUnique = false;
                    conflictingIndex = idx;
                    break;
                }
            }
            if(!isUnique) {
                alert(`Key "${newKey}" is already used by ${instruments[conflictingIndex].name}. Choose another.`);
                return;
            }
            const oldKey = getKeyForIndex(index);
            keyMap.delete(oldKey);
            keyMap.set(newKey, index);
            const labelSpan = document.getElementById(`key-label-${index}`);
            if(labelSpan) labelSpan.textContent = newKey;
            closeModal();
            updateStatus(`Key for ${instruments[index].name} changed to ${newKey}`);
        };
        
        saveBtn.addEventListener('click', saveKey);
        input.addEventListener('keydown', (e) => {
            if(e.key === 'Enter') {
                e.preventDefault();
                saveKey();
            }
        });
        overlay.addEventListener('click', (e) => {
            if(e.target === overlay) closeModal();
        });
        input.focus();
    }
    
    function getKeyForIndex(index) {
        for(let [key, idx] of keyMap.entries()) {
            if(idx === index) return key;
        }
        return instruments[index].defaultKey;
    }
    
    function playSoundByIdx(index, triggerVisual = true) {
        if(isPlayingSequence) return;
        const instrument = instruments[index];
        playSound(instrument.sound);
        if(triggerVisual) {
            const pad = pads[index];
            if(pad) {
                pad.classList.add('active');
                setTimeout(() => pad.classList.remove('active'), 150);
            }
        }
    }
    
    async function startSequence() {
        if(isPlayingSequence) return;
        const inputField = document.getElementById('sequenceInput');
        let seq = inputField.value.toUpperCase();
        if(seq.length === 0) {
            updateStatus('Please enter a sequence first!', true);
            return;
        }
        let validSeq = [];
        for(let ch of seq) {
            if(keyMap.has(ch)) validSeq.push(ch);
        }
        if(validSeq.length === 0) {
            updateStatus('No valid keys found in sequence!', true);
            return;
        }
        
        isPlayingSequence = true;
        const playBtn = document.getElementById('playSeqBtn');
        const seqInput = document.getElementById('sequenceInput');
        playBtn.disabled = true;
        seqInput.disabled = true;
        playBtn.classList.add('disabled');
        seqInput.style.opacity = '0.6';
        updateStatus(`Playing sequence: ${validSeq.join('')}...`);
        
        for(let i = 0; i < validSeq.length; i++) {
            if(!isPlayingSequence) break;
            const key = validSeq[i];
            const idx = keyMap.get(key);
            if(idx !== undefined) {
                const pad = pads[idx];
                pad.classList.add('active');
                const instrument = instruments[idx];
                playSound(instrument.sound);
                await delay(200);
                pad.classList.remove('active');
                if(i < validSeq.length - 1) await delay(300);
            } else {
                await delay(100);
            }
        }
        
        isPlayingSequence = false;
        playBtn.disabled = false;
        seqInput.disabled = false;
        playBtn.classList.remove('disabled');
        seqInput.style.opacity = '1';
        updateStatus('✅ Sequence finished! Ready to play.');
    }
    
    function delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
    
    function updateStatus(msg, isError = false) {
        const statusDiv = document.getElementById('statusMsg');
        if(statusDiv) {
            statusDiv.textContent = msg;
            statusDiv.style.color = isError ? '#ffaa88' : '#d4f1f9';
            setTimeout(() => {
                if(statusDiv && !isPlayingSequence) statusDiv.style.color = '#ffcf9a';
            }, 2500);
        }
    }
    
    function handleKeyDown(e) {
        if(isPlayingSequence) {
            e.preventDefault();
            return;
        }
        let key = e.key.toUpperCase();
        if(key.length === 1 && /[A-Z]/i.test(key)) {
            e.preventDefault();
            if(keyMap.has(key)) {
                unlockAudio();
                const idx = keyMap.get(key);
                playSoundByIdx(idx, true);
                const pad = pads[idx];
                pad.classList.add('active');
                setTimeout(() => pad.classList.remove('active'), 120);
            }
        }
    }
    
    function init() {
        buildUI();
        window.addEventListener('keydown', handleKeyDown);
        updateStatus('🎧 Click any pad or press A/S/D/F/G/H/J to start!');
    }
    
    init();
})();