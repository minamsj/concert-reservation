import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 50 },
        { duration: '20s', target: 50 },
        { duration: '10s', target: 0 },
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'],   // 실패율 1% 미만
        http_req_duration: ['p(95)<1000'] // 95%의 요청이 1초 이내 처리
    },
};

const BASE_URL = 'http://host.docker.internal:8080';

export default function () {
    const userId = Math.floor(Math.random() * 10) + 1;
    console.log(`Generated userId: ${userId}`);

    // 포인트 충전 (쿼리 파라미터로 userId와 amount 전달)
    const chargeRes = http.post(`${BASE_URL}/api/point/charge?userId=${userId}&amount=10000`);

    check(chargeRes, {
        'charge status is 200': (r) => r.status === 200,
    });

    sleep(1);

    // 포인트 조회
    const getRes = http.get(`${BASE_URL}/api/point/${userId}`);

    check(getRes, {
        'get status is 200': (r) => r.status === 200
    });
}
