import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 50 },
        { duration: '20s', target: 50 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000']
    },
};

const BASE_URL = 'http://host.docker.internal:8080';

export default function () {
    const payload = {
        userId: Math.floor(Math.random() * 10) + 1,
        schedulesId: 1,
        seatNum: Math.floor(Math.random() * 50) + 1,
        concertId: 1,
        point: 20000
    };

    const reservationRes = http.post(
        `${BASE_URL}/api/concert/reservation`,
        JSON.stringify(payload),
        {
            headers: { 'Content-Type': 'application/json' }
        }
    );

    check(reservationRes, {
        'reservation status is 200': (r) => r.status === 200
    });

    sleep(1);
}