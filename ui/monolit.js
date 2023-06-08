function sendPostRequest(event) {
    event.preventDefault();
    const countInput = document.getElementById('countInput');
    const executionTimeElement = document.getElementById('executionTime');

    let countValue = countInput.value ? countInput.value : 0;

    const startTime = performance.now();

    const requests = [];

    while (countValue !== 0) {
        countValue--;
        const data = {
            name: generateUniqueValue(),
            email: generateUniqueValue()+'@gmail.com',
            years: generateRandomYear()
        };

        const request = fetch('http://localhost:8086/api/main-service/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(result => {
                console.log('Response:', result);
            })
            .catch(error => {
                console.error('Error:', error);
            });

        requests.push(request);
    }

    Promise.all(requests)
        .then(() => {
            const endTime = performance.now();
            const executionTime = endTime - startTime;
            executionTimeElement.textContent = 'Execution time: ' + executionTime.toFixed(2) + ' ms';
        });
}

function generateUniqueValue() {
    const timestamp = new Date().getTime();
    const random = Math.random().toString(36).substr(2, 9);
    const uniqueValue = timestamp.toString(36) + random;
    return uniqueValue;
}

function generateRandomYear() {
    const min = 2000;
    const max = 2022;
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
function statisticGenerate(event) {
    event.preventDefault();
    const executionTimeElement = document.getElementById('executionTimeStatistic');


    const startTime = performance.now();

    const requests = [];


    const request = fetch('http://localhost:8086/api/main-service/create-statistic-by-years', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(result => {
            console.log('Response:', result);
        })
        .catch(error => {
            console.error('Error:', error);
        });

    requests.push(request);


    Promise.all(requests)
        .then(() => {
            const endTime = performance.now();
            const executionTime = endTime - startTime;
            executionTimeElement.textContent = 'Execution time: ' + executionTime.toFixed(2) + ' ms';
        });
}
function getStatistic() {
    event.preventDefault();
    const tableBody = document.getElementById('tableBody');

    fetch('http://localhost:8086/api/main-service/get-statistic-by-years')
        .then(response => response.json())
        .then(data => {
            tableBody.innerHTML = '';

            data.forEach(item => {
                const row = document.createElement('tr');
                const yearCell = document.createElement('td');
                const reversibilityCell = document.createElement('td');

                yearCell.textContent = item.year;
                reversibilityCell.textContent = item.averageReversibility;
                console.log( reversibilityCell.textContent)
                row.appendChild(yearCell);
                row.appendChild(reversibilityCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


function getYear(event) {
    event.preventDefault();
    const startTime = performance.now();
    const tableBody = document.getElementById('tableBody2');

    const requests = [];
    const executionTimeElement = document.getElementById('executionTimeYer');
    const executionElement = document.getElementById('executionYer');
    let res1;
    const request = fetch('http://localhost:8086/api/main-service/get-success-years', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            tableBody.innerHTML = '';
            console.log(data)

            const row = document.createElement('tr');
            const yearCell = document.createElement('td');
            const reversibilityCell = document.createElement('td');

            yearCell.textContent = data.year;
            reversibilityCell.textContent = data.averageReversibility;

            row.appendChild(yearCell);
            row.appendChild(reversibilityCell);

            tableBody.appendChild(row);

        })
        .catch(error => {
            console.error('Error:111', error);
        });

    requests.push(request);


    Promise.all(requests)
        .then(() => {
            const endTime = performance.now();
            const executionTime = endTime - startTime;
            executionTimeElement.textContent = 'Execution time: ' + executionTime.toFixed(2) + ' ms';

        });
}