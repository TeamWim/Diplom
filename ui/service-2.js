function statisticGenerate(event) {
    event.preventDefault();
    const executionTimeElement = document.getElementById('executionTimeStatistic');


    const startTime = performance.now();

    const requests = [];


        const request = fetch('http://localhost:8082/api/statistic/create-statistic-by-years', {
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

    fetch('http://localhost:8082/api/statistic/get-statistic-by-years')
        .then(response => response.json())
        .then(data => {
            tableBody.innerHTML = '';

            data.forEach(item => {
                const row = document.createElement('tr');
                const yearCell = document.createElement('td');
                const reversibilityCell = document.createElement('td');

                yearCell.textContent = item.year;
                reversibilityCell.textContent = item.averageReversibility;

                row.appendChild(yearCell);
                row.appendChild(reversibilityCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


