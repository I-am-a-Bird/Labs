// ========== ПЕРЕКЛЮЧЕНИЕ МЕЖДУ ЛАБАМИ ==========
document.querySelectorAll('.lab-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        document.querySelectorAll('.lab-btn').forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        const labId = btn.getAttribute('data-lab');
        document.querySelectorAll('.lab-panel').forEach(panel => panel.classList.remove('active'));
        document.getElementById(labId).classList.add('active');
    });
});

// Вспомогательная функция показа результатов
function showResult(elementId, html) {
    const div = document.getElementById(elementId);
    if (div) {
        div.innerHTML = `<strong>Результат:</strong><br>${html}`;
        div.style.display = 'block';
    }
}

// ==================== ЛАБА 2 ====================
const alphabet22 = 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФ';
const indicesLab2 = [3, 5, 6, 12, 20, 21];
const variantNum = 4;

function lab2_task1() {
    let output = '';
    for (let i = 0; i < indicesLab2.length; i++) {
        let char = alphabet22[indicesLab2[i] - 1];
        if (i === indicesLab2.length - 1) output += char + variantNum;
        else output += char + '-' + variantNum + '-';
    }
    showResult('lab2_result1', output);
    alert(output);
}

function lab2_task2() {
    let digits = indicesLab2.map(n => n.toString().padStart(2, '0')).join('');
    let n1 = parseInt(digits.slice(0, 3));
    let n2 = parseInt(digits.slice(3, 6));
    let n3 = parseInt(digits.slice(6, 9));
    let n4 = parseInt(digits.slice(9, 12));
    let expr = `(${n1} * ${n2}) / (${n3} + ${n4})`;
    let res = (n1 * n2) / (n3 + n4);
    showResult('lab2_result2', `Числа: ${n1}, ${n2}, ${n3}, ${n4}<br>Выражение: ${expr}<br>Результат: ${res.toFixed(4)}`);
    alert(`Результат: ${res.toFixed(4)}`);
}

function lab2_task3() {
    let str = indicesLab2.map(i => alphabet22[i - 1]).join('');
    showResult('lab2_result3', `Построенная строка: ${str}`);
}

function lab2_task4() {
    let choice = confirm('OK → y = (a² + b)/c\nОтмена → f = d/(5 + e²)');
    let a = 4, b = 3, c = 2, d = 12, e = 2;
    let result, formula;
    if (choice) {
        result = (a * a + b) / c;
        formula = `y = (${a}² + ${b}) / ${c} = ${result.toFixed(4)}`;
    } else {
        result = d / (5 + e * e);
        formula = `f = ${d} / (5 + ${e}²) = ${result.toFixed(4)}`;
    }
    showResult('lab2_result4', formula);
    document.writeln(`<div style="background:#e6f4ea; padding:16px; margin:20px; border-radius:16px;"><strong>writeln:</strong> ${formula}</div>`);
}

// ==================== ЛАБА 3 ====================
function lab3_task1() {
    let num = 264.289;
    showResult('lab3_result1', `Исходное: ${num}<br>Модуль (Math.abs): ${Math.abs(num)}<br>Округление toFixed(2): ${num.toFixed(2)}`);
}

function lab3_task2() {
    let arr = [2.5, 1.2, 3.8, 4.1, 0.7, 5.6, 2.2, 3.3];
    let powers = arr.map(v => Math.pow(v, 6));
    let sum = powers.reduce((a, b) => a + b, 0);
    showResult('lab3_result2', `Массив: [${arr.join(', ')}]<br>6-е степени: [${powers.map(p => p.toFixed(4)).join(', ')}]<br>Сумма: ${sum.toFixed(6)}`);
}

function lab3_task3() {
    let test = 87.651;
    showResult('lab3_result3', `Число: ${test}<br>Math.abs: ${Math.abs(test)}<br>Math.round: ${Math.round(test)}<br>Math.ceil: ${Math.ceil(test)}<br>Math.floor: ${Math.floor(test)}<br>Math.sqrt(100): ${Math.sqrt(100)}<br>Math.pow(2,5): ${Math.pow(2, 5)}<br>Math.random(): ${Math.random().toFixed(4)}`);
}

// ==================== ЛАБА 4 ====================
function lab4_task1() {
    let lang = 'анг';
    let days;
    if (lang === 'рус') days = ['Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб', 'Вс'];
    else if (lang === 'анг') days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
    else if (lang === 'бел') days = ['Пан', 'Аўт', 'Сер', 'Чцв', 'Пят', 'Суб', 'Няд'];
    else if (lang === 'нем') days = ['Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa', 'So'];
    else days = ['Ошибка'];
    showResult('lab4_result1', `Язык: ${lang}<br>Дни недели: ${days.join(', ')}`);
}

function lab4_task2() {
    let str = 'ab12cde345';
    let k = 8;
    let char = str[k - 1];
    let isLetter = /[a-zA-Z]/.test(char);
    let digits = str.match(/\d/g) || [];
    let sumDigits = digits.reduce((s, d) => s + parseInt(d), 0);
    showResult('lab4_result2', `Строка: ${str}<br>Символ ${k}: "${char}"<br>Буква: ${isLetter ? 'Да' : 'Нет'}<br>Сумма цифр: ${sumDigits}<br>Сумма четная: ${sumDigits % 2 === 0 ? 'Да' : 'Нет'}`);
}

function lab4_task3() {
    let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    let day = now.getDate();
    let season;
    if (month >= 3 && month <= 5) season = 'Весна';
    else if (month >= 6 && month <= 8) season = 'Лето';
    else if (month >= 9 && month <= 11) season = 'Осень';
    else season = 'Зима';
    let decade;
    if (day <= 10) decade = '1-я декада';
    else if (day <= 20) decade = '2-я декада';
    else decade = '3-я декада';
    let months = ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'];
    let isLeap = (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
    showResult('lab4_result3', `Дата: ${day}.${month}.${year}<br>Время года (ВГ): ${season}<br>Декада (ДМ): ${decade}<br>Месяц (МГ): ${months[month - 1]}<br>Год (ВсГ): ${isLeap ? 'Високосный' : 'Не високосный'}`);
}

function lab4_task4() {
    let arr = Array.from({ length: 25 }, () => +(Math.random() * 100).toFixed(2));
    let sumEven = 0;
    let productOddIndex = 1;
    let sumMultiple3 = 0;
    let productOddValue = 1;
    for (let i = 0; i < arr.length; i++) {
        let val = arr[i];
        let num = i + 1;
        if (val % 2 === 0) sumEven += val;
        if (num % 2 !== 0) productOddIndex *= val;
        if (num % 3 === 0) sumMultiple3 += val;
        if (Math.floor(val) % 2 !== 0) productOddValue *= val;
    }
    showResult('lab4_result4', `Массив (25 эл.): [${arr.map(v => v.toFixed(2)).join(', ')}]<br><br>СЧЭ (сумма четных элементов): ${sumEven.toFixed(4)}<br>ПЭНН (произведение элементов с нечетными номерами): ${productOddIndex.toFixed(4)}<br>СЭКЗ (сумма элементов, номера кратны 3): ${sumMultiple3.toFixed(4)}<br>ПНЧЭ (произведение нечетных элементов массива): ${productOddValue.toFixed(4)}`);
}

// ==================== ЛАБА 5 ====================
function lab5_task1() {
    let arr = [];
    for (let i = 0; i < 14; i++) {
        let x = Math.random() * 10 + 0.1;
        arr.push(1 / Math.tan(x));
    }
    arr.sort((a, b) => b - a);
    showResult('lab5_result1', `Массив (ctg(x), сортировка по убыванию):<br>[${arr.map(v => v.toFixed(4)).join(', ')}]`);
}

function lab5_task2() {
    let now = new Date();
    let formatted = `${now.getDate()}.${now.getHours()}:${now.getMinutes()}`;
    let parseMs = Date.parse(now);
    let seconds = Math.floor(now.getTime() / 1000);
    showResult('lab5_result2', `Текущая дата (день, час, минута): ${formatted}<br>Date.parse (мс с 01.01.1970): ${parseMs}<br>getTime (секунд с 01.01.1970): ${seconds}`);
}

function lab5_task3() {
    let a = 5;
    let area = (3 * Math.sqrt(3) / 2) * a * a;
    showResult('lab5_result3', `Площадь правильного шестиугольника со стороной ${a}: ${area.toFixed(4)}`);
}

// ==================== ЛАБА 6 ====================
function lab6_task1() {
    let R = [7, 5, 8];
    let m = 2, n = 3;
    for (let i = 0; i < m; i++) R.unshift(Math.floor(Math.random() * 100));
    for (let i = 0; i < n; i++) R.push('text' + (i + 1));
    showResult('lab6_result1', `Результат: [${R.join(', ')}]`);
}

function lab6_task2() {
    let rows = 4, cols = 6;
    let matrix = Array.from({ length: rows }, () => Array.from({ length: cols }, () => +(Math.random() * 100).toFixed(2)));
    for (let i = 0; i < rows; i += 2) {
        matrix[i].sort((a, b) => a - b);
    }
    showResult('lab6_result2', `Матрица ${rows}x${cols}, четные строки отсортированы по возрастанию:<br>${matrix.map(row => row.join(', ')).join('<br>')}`);
}

function lab6_task3() {
    let rows = 5, cols = 8;
    let matrix = Array.from({ length: rows }, () => Array.from({ length: cols }, () => Math.random() > 0.5 ? Math.floor(Math.random() * 100) : 'text' + Math.floor(Math.random() * 10)));
    let joined = matrix.map(row => row.join(',')).join(' | ');
    let reversed = joined.split('').reverse().join('');
    showResult('lab6_result3', `После join и reverse (первые 500 символов):<br>${reversed.substring(0, 500)}...`);
}

function lab6_task4() {
    let rows = 5, cols = 7;
    let matrix = Array.from({ length: rows }, () => Array.from({ length: cols }, () => 'word' + Math.floor(Math.random() * 100)));
    for (let i = 0; i < 1; i++) matrix.unshift(['new' + i]);
    for (let i = 0; i < 2; i++) matrix.pop();
    showResult('lab6_result4', `Матрица после операций:<br>${matrix.map(row => row.join(',')).join('<br>')}`);
}

// ==================== ЛАБА 7 ====================
function lab7_task1() {
    let win = window.open('', 'popupWindow', 'width=400,height=300,left=200,top=100');
    win.document.write(`
        <html>
        <head><title>Всплывающее окно</title></head>
        <body style="font-family:Arial;text-align:center;margin-top:50px;">
            <h3>Линия</h3>
            <hr style="width:57%; border:2px solid blue;">
            <p>Длина линии: 57%, толщина: 2px</p>
            <button onclick="window.close()">Закрыть</button>
        </body>
        </html>
    `);
    showResult('lab7_result1', 'Всплывающее окно открыто. Если браузер заблокировал, разрешите попап-окна.');
}

function lab7_task2() {
    let formHtml = `
        <div style="background:#f0f0f0; padding:16px; border-radius:12px;">
            <h4>Анкета сотрудника</h4>
            <p><strong>Место рождения (МР):</strong> Москва</p>
            <p><strong>Должность:</strong> Программист</p>
            <p><strong>Пол:</strong> Мужской</p>
        </div>
    `;
    showResult('lab7_result2', formHtml);
}

function lab7_task3() {
    let formHtml = `
        <div style="background:#f0f0f0; padding:16px; border-radius:12px;">
            <h4>Анкета слушателя курсов</h4>
            <p><strong>Курс:</strong> Второй</p>
            <p><strong>Язык общения:</strong> Русский</p>
            <p><strong>Форма образования (ФО):</strong> Очная</p>
            <p><strong>Стоимость курса:</strong> 25000 руб.</p>
            <p><strong>Итоговая стоимость:</strong> 50000 руб.</p>
        </div>
    `;
    showResult('lab7_result3', formHtml);
}

// ==================== ЛАБА 8 ====================
let lab8ImageIndex = 1;
function lab8_changeImage() {
    const img = document.getElementById('lab8_image');
    if (img) {
        lab8ImageIndex++;
        if (lab8ImageIndex > 3) lab8ImageIndex = 1;
        img.src = `https://placehold.co/300x200/2c7da0/white?text=Image+${lab8ImageIndex}`;
        showResult('lab8_result1', `Изображение изменено на Image ${lab8ImageIndex}`);
    }
}
function lab8_resetImage() {
    const img = document.getElementById('lab8_image');
    if (img) {
        lab8ImageIndex = 1;
        img.src = 'https://placehold.co/300x200/2c7da0/white?text=Image+1';
        showResult('lab8_result1', 'Изображение возвращено к исходному');
    }
}

// ==================== ЛАБА 9 ====================
function lab9_textOver() {
    const elem = document.getElementById('lab9_text');
    if (elem) {
        elem.style.fontSize = '42pt';
        elem.style.backgroundColor = '#cfe4f0';
    }
}
function lab9_textOut() {
    const elem = document.getElementById('lab9_text');
    if (elem) {
        elem.style.fontSize = '24px';
        elem.style.backgroundColor = '#e6f0f5';
    }
}
function lab9_imageClick() {
    const img = document.getElementById('lab9_image');
    if (img) {
        img.src = 'https://placehold.co/300x200/00bfff/white?text=New+Image';
        showResult('lab9_result', 'Изображение изменено при клике!');
    }
}
function lab9_captionClick() {
    const caption = document.getElementById('lab9_caption');
    if (caption) {
        caption.style.color = '#00bfff';
        caption.style.fontSize = '22px';
        showResult('lab9_result', 'Подпись изменена! Цвет шрифта стал голубым.');
    }
}