(function() {
    // ---------- БАНК СЛОВ (30+ слов с пробелами, дефисами) ----------
    const WORDS = [
        "ПРОГРАММИРОВАНИЕ", "ВИСЕЛИЦА", "РАЗРАБОТЧИК", "АЛГОРИТМ",
        "СТРУКТУРА ДАННЫХ", "ФРОНТЕНД", "БЭКЕНД", "КЛАВИАТУРА",
        "МОНИТОР", "ОПЕРАЦИОННАЯ СИСТЕМА", "БРАУЗЕР", "ИНТЕРФЕЙС",
        "АДАПТИВНАЯ ВЁРСТКА", "КИБЕРБЕЗОПАСНОСТЬ", "ДЕБАГГИНГ",
        "КОМПИЛЯТОР", "ИНТЕРПРЕТАТОР", "ФРЕЙМВОРК", "БИБЛИОТЕКА",
        "СЕРВЕР", "КЛИЕНТ", "ЗАПРОС", "ОТВЕТ", "СЕССИЯ",
        "АУТЕНТИФИКАЦИЯ", "АВТОРИЗАЦИЯ", "ТОКЕН", "API",
        "КОНТЕЙНЕРИЗАЦИЯ", "ДОКЕР", "ОРКЕСТРАЦИЯ", "КУБЕРНЕТИС",
        "РЕАКТИВНОЕ ПРОГРАММИРОВАНИЕ", "ПАТТЕРН ПРОЕКТИРОВАНИЯ"
    ];

    const MAX_MISTAKES = 6;
    let currentWord = "";
    let currentDisplay = [];
    let mistakes = 0;
    let usedLetters = new Set();      // все использованные буквы
    let mistakeLetters = new Set();   // ошибочные буквы
    let gameActive = true;
    let hintUsed = false;

    // Маппинг русских букв -> английские клавиши (физическая клавиатура)
    const RUS_TO_ENG_KEY = {
        'А':'A','Б':'B','В':'V','Г':'G','Д':'D','Е':'E','Ё':'E',
        'Ж':'ZH','З':'Z','И':'I','Й':'Y','К':'K','Л':'L','М':'M',
        'Н':'N','О':'O','П':'P','Р':'R','С':'S','Т':'T','У':'U',
        'Ф':'F','Х':'H','Ц':'C','Ч':'CH','Ш':'SH','Щ':'SCH','Ъ':'`',
        'Ы':'Y','Ь':'`','Э':'E','Ю':'YU','Я':'YA'
    };

    // Русские буквы для клавиатуры (порядок как на стандартной)
    const RUSSIAN_LETTERS = [
        'Й','Ц','У','К','Е','Н','Г','Ш','Щ','З','Х','Ъ',
        'Ф','Ы','В','А','П','Р','О','Л','Д','Ж','Э',
        'Я','Ч','С','М','И','Т','Ь','Б','Ю','Ё'
    ];

    // DOM элементы (будут созданы динамически)
    let canvas, ctx;
    let wordDisplayDiv;
    let attemptsSpan, mistakesSpan;
    let usedContainer;
    let keyboardContainer;
    let messageDiv;
    let hintBtn;

    // ---------- ИНИЦИАЛИЗАЦИЯ DOM через createElement ----------
    function buildDOM() {
        const gameContainer = document.createElement('div');
        gameContainer.className = 'game-container';
        
        const layout = document.createElement('div');
        layout.className = 'game-layout';
        
        // левая панель
        const gallowsPanel = document.createElement('div');
        gallowsPanel.className = 'gallows-panel';
        const canvasWrapper = document.createElement('div');
        canvasWrapper.className = 'canvas-wrapper';
        canvas = document.createElement('canvas');
        canvas.width = 400;
        canvas.height = 350;
        canvas.style.width = '100%';
        canvas.style.height = 'auto';
        canvas.style.maxWidth = '400px';
        canvas.getContext('2d');
        canvasWrapper.appendChild(canvas);
        gallowsPanel.appendChild(canvasWrapper);
        layout.appendChild(gallowsPanel);
        
        // правая панель
        const gamePanel = document.createElement('div');
        gamePanel.className = 'game-panel';
        
        // слово
        const wordArea = document.createElement('div');
        wordArea.className = 'word-area';
        wordDisplayDiv = document.createElement('div');
        wordDisplayDiv.className = 'word-display';
        wordArea.appendChild(wordDisplayDiv);
        gamePanel.appendChild(wordArea);
        
        // статистика
        const statsDiv = document.createElement('div');
        statsDiv.className = 'stats';
        attemptsSpan = document.createElement('div');
        attemptsSpan.className = 'stats-card';
        mistakesSpan = document.createElement('div');
        mistakesSpan.className = 'stats-card';
        statsDiv.appendChild(attemptsSpan);
        statsDiv.appendChild(mistakesSpan);
        gamePanel.appendChild(statsDiv);
        
        // использованные буквы
        const usedDiv = document.createElement('div');
        usedDiv.className = 'used-letters';
        const usedTitle = document.createElement('div');
        usedTitle.className = 'used-title';
        usedTitle.textContent = 'ИСПОЛЬЗОВАННЫЕ БУКВЫ';
        usedContainer = document.createElement('div');
        usedContainer.className = 'used-badges';
        usedDiv.appendChild(usedTitle);
        usedDiv.appendChild(usedContainer);
        gamePanel.appendChild(usedDiv);
        
        // клавиатура
        keyboardContainer = document.createElement('div');
        keyboardContainer.className = 'keyboard';
        gamePanel.appendChild(keyboardContainer);
        
        // кнопки действий
        const actionDiv = document.createElement('div');
        actionDiv.className = 'action-buttons';
        const newGameBtn = document.createElement('button');
        newGameBtn.textContent = '🔄 НОВАЯ ИГРА';
        newGameBtn.className = 'action-btn';
        newGameBtn.addEventListener('click', () => resetGame());
        hintBtn = document.createElement('button');
        hintBtn.textContent = '💡 ПОДСКАЗКА (1)';
        hintBtn.className = 'action-btn';
        hintBtn.addEventListener('click', () => giveHint());
        actionDiv.appendChild(newGameBtn);
        actionDiv.appendChild(hintBtn);
        gamePanel.appendChild(actionDiv);
        
        messageDiv = document.createElement('div');
        messageDiv.className = 'message-area';
        gamePanel.appendChild(messageDiv);
        
        layout.appendChild(gamePanel);
        gameContainer.appendChild(layout);
        document.body.appendChild(gameContainer);
        
        ctx = canvas.getContext('2d');
        drawGallows(0);
    }
    
    // Отрисовка виселицы (6 этапов)
    function drawGallows(step) {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.lineWidth = 3;
        ctx.strokeStyle = '#3a2a1f';
        ctx.fillStyle = '#2c1e12';
        // основание
        ctx.fillRect(30, 320, 200, 12);
        ctx.fillRect(120, 280, 12, 50);
        ctx.fillRect(120, 40, 12, 250);
        ctx.fillRect(120, 40, 140, 12);
        ctx.fillRect(240, 40, 8, 40);
        // верёвка
        ctx.beginPath();
        ctx.moveTo(244, 80);
        ctx.lineTo(244, 110);
        ctx.stroke();
        
        // голова
        if (step >= 1) {
            ctx.beginPath();
            ctx.arc(244, 135, 22, 0, Math.PI * 2);
            ctx.stroke();
        }
        // туловище
        if (step >= 2) {
            ctx.beginPath();
            ctx.moveTo(244, 157);
            ctx.lineTo(244, 230);
            ctx.stroke();
        }
        // левая рука
        if (step >= 3) {
            ctx.beginPath();
            ctx.moveTo(244, 180);
            ctx.lineTo(210, 210);
            ctx.stroke();
        }
        // правая рука
        if (step >= 4) {
            ctx.beginPath();
            ctx.moveTo(244, 180);
            ctx.lineTo(278, 210);
            ctx.stroke();
        }
        // левая нога
        if (step >= 5) {
            ctx.beginPath();
            ctx.moveTo(244, 230);
            ctx.lineTo(215, 280);
            ctx.stroke();
        }
        // правая нога
        if (step >= 6) {
            ctx.beginPath();
            ctx.moveTo(244, 230);
            ctx.lineTo(273, 280);
            ctx.stroke();
        }
    }
    
    // Обновление отображения слова
    function updateWordDisplay() {
        wordDisplayDiv.textContent = currentDisplay.join(' ');
    }
    
    // Обновление панели использованных букв
    function updateUsedLettersUI() {
        usedContainer.innerHTML = '';
        const allUsed = Array.from(usedLetters).sort();
        allUsed.forEach(letter => {
            const badge = document.createElement('span');
            badge.className = 'badge';
            if (mistakeLetters.has(letter)) {
                badge.classList.add('mistake');
            }
            badge.textContent = letter;
            usedContainer.appendChild(badge);
        });
    }
    
    // Обновление состояния клавиатуры
    function updateKeyboardState() {
        const allBtns = keyboardContainer.querySelectorAll('.key-btn');
        allBtns.forEach(btn => {
            const letter = btn.getAttribute('data-letter');
            if (usedLetters.has(letter)) {
                btn.disabled = true;
                btn.classList.add('disabled');
            } else {
                btn.disabled = !gameActive;
                if (!gameActive) btn.classList.add('disabled');
                else btn.classList.remove('disabled');
            }
        });
    }
    
    // Обработка нажатой буквы
    function handleGuess(letter) {
        if (!gameActive) return;
        if (usedLetters.has(letter)) return;
        
        usedLetters.add(letter);
        const upperLetter = letter.toUpperCase();
        let isCorrect = false;
        
        // проверка в слове
        for (let i = 0; i < currentWord.length; i++) {
            if (currentWord[i] === upperLetter && currentDisplay[i] === '_') {
                currentDisplay[i] = upperLetter;
                isCorrect = true;
            }
        }
        
        if (!isCorrect) {
            mistakes++;
            mistakeLetters.add(letter);
            drawGallows(mistakes);
        }
        
        updateWordDisplay();
        updateUsedLettersUI();
        updateKeyboardState();
        
        // обновление счётчиков
        attemptsSpan.textContent = `❤️ ПОПЫТКИ: ${MAX_MISTAKES - mistakes}/${MAX_MISTAKES}`;
        mistakesSpan.textContent = `❌ ОШИБКИ: ${mistakes}`;
        
        // проверка победы
        const isWin = !currentDisplay.includes('_');
        if (isWin) {
            gameActive = false;
            messageDiv.innerHTML = '🎉 ПОБЕДА! 🎉<br>Вы угадали слово!';
            updateKeyboardState();
            return;
        }
        
        // проверка поражения
        if (mistakes >= MAX_MISTAKES) {
            gameActive = false;
            messageDiv.innerHTML = `💀 ПОРАЖЕНИЕ! 💀<br>Загаданное слово: ${currentWord}`;
            updateKeyboardState();
            return;
        }
    }
    
    // Новая игра
    function resetGame() {
        // выбор случайного слова
        const randomIndex = Math.floor(Math.random() * WORDS.length);
        currentWord = WORDS[randomIndex].toUpperCase();
        currentDisplay = [];
        for (let ch of currentWord) {
            if (/[А-ЯЁ]/.test(ch)) {
                currentDisplay.push('_');
            } else {
                currentDisplay.push(ch);
            }
        }
        mistakes = 0;
        usedLetters.clear();
        mistakeLetters.clear();
        gameActive = true;
        hintUsed = false;
        hintBtn.textContent = '💡 ПОДСКАЗКА (1)';
        drawGallows(0);
        updateWordDisplay();
        updateUsedLettersUI();
        attemptsSpan.textContent = `❤️ ПОПЫТКИ: ${MAX_MISTAKES}/${MAX_MISTAKES}`;
        mistakesSpan.textContent = `❌ ОШИБКИ: 0`;
        messageDiv.innerHTML = '✨ Новая игра! Угадайте слово ✨';
        updateKeyboardState();
    }
    
    // Подсказка (первая буква или определение)
    function giveHint() {
        if (!gameActive) {
            messageDiv.innerHTML = 'Игра окончена, начните новую игру для подсказки.';
            return;
        }
        if (hintUsed) {
            messageDiv.innerHTML = 'Подсказка уже использована!';
            return;
        }
        // открываем первую ещё не угаданную букву
        for (let i = 0; i < currentDisplay.length; i++) {
            if (currentDisplay[i] === '_' && /[А-ЯЁ]/.test(currentWord[i])) {
                const letter = currentWord[i];
                if (!usedLetters.has(letter)) {
                    handleGuess(letter);
                    hintUsed = true;
                    hintBtn.textContent = '💡 ПОДСКАЗКА (ИСП.)';
                    messageDiv.innerHTML = `Подсказка: буква "${letter}" открыта!`;
                    return;
                }
            }
        }
        messageDiv.innerHTML = 'Нет доступных букв для подсказки!';
    }
    
    // Генерация виртуальной клавиатуры
    function buildKeyboard() {
        keyboardContainer.innerHTML = '';
        const rows = [
            ['Й','Ц','У','К','Е','Н','Г','Ш','Щ','З','Х','Ъ'],
            ['Ф','Ы','В','А','П','Р','О','Л','Д','Ж','Э'],
            ['Я','Ч','С','М','И','Т','Ь','Б','Ю','Ё']
        ];
        
        rows.forEach(row => {
            const rowDiv = document.createElement('div');
            rowDiv.className = 'key-row';
            row.forEach(letter => {
                const btn = document.createElement('button');
                btn.className = 'key-btn';
                btn.setAttribute('data-letter', letter);
                // подсказка с клавишей
                const engKey = RUS_TO_ENG_KEY[letter] || letter;
                btn.innerHTML = `${letter} <span class="key-hint">${engKey}</span>`;
                btn.addEventListener('click', (e) => {
                    e.preventDefault();
                    if (gameActive && !usedLetters.has(letter)) {
                        // анимация active
                        btn.style.transform = 'translateY(2px)';
                        setTimeout(() => { btn.style.transform = ''; }, 100);
                        handleGuess(letter);
                    }
                });
                rowDiv.appendChild(btn);
            });
            keyboardContainer.appendChild(rowDiv);
        });
    }
    
    // Физическая клавиатура
    function initPhysicalKeyboard() {
        window.addEventListener('keydown', (e) => {
            if (!gameActive) return;
            const key = e.key.toUpperCase();
            // предотвращаем многократное срабатывание
            e.preventDefault();
            
            // Маппинг английских клавиш в русские буквы
            let foundLetter = null;
            for (let [rus, eng] of Object.entries(RUS_TO_ENG_KEY)) {
                if (eng === key) {
                    foundLetter = rus;
                    break;
                }
            }
            // дополнительно если сам русский ввели
            if (!foundLetter && RUSSIAN_LETTERS.includes(key)) {
                foundLetter = key;
            }
            
            if (foundLetter && !usedLetters.has(foundLetter)) {
                // эмуляция нажатия виртуальной кнопки
                const virtualBtn = Array.from(document.querySelectorAll('.key-btn')).find(
                    btn => btn.getAttribute('data-letter') === foundLetter
                );
                if (virtualBtn) {
                    virtualBtn.style.transform = 'translateY(2px)';
                    setTimeout(() => { if(virtualBtn) virtualBtn.style.transform = ''; }, 120);
                }
                handleGuess(foundLetter);
            }
        });
    }
    
    // Старт игры
    function initGame() {
        buildDOM();
        buildKeyboard();
        initPhysicalKeyboard();
        resetGame();
    }
    
    initGame();
})();