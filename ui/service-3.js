function getYear(event) {
    event.preventDefault();
    const startTime = performance.now();
    const tableBody = document.getElementById('tableBody2');

    const requests = [];
    const executionTimeElement = document.getElementById('executionTimeYer');
    const executionElement = document.getElementById('executionYer');
    let res1;
    const request = fetch('http://localhost:8084/api/success/get-success-years', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            tableBody.innerHTML = '';
            console.log(data)
            console.log("1111")

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