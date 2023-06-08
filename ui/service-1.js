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

        const request = fetch('http://localhost:8080/api/student/create', {
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
