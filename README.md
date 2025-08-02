기본 URL: /api/calendars
API 목록
1. 일정 생성
: 새로운 일정을 생성합니다.

요청 Request Body:
{
  "title": "할 일 제목",
  "content": "상세 내용",
  "author": "작성자 이름",
  "password": "비밀번호"
}

{
  "id": 1,
  "title": "할 일 제목",
  "content": "상세 내용",
  "author": "작성자 이름",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T10:00:00"
}

2. 전체 또는 작성자별 일정 조회
: 모든 일정을 조회하거나, 특정 작성자의 일정만 조회합니다. 결과는 수정일 기준 내림차순으로 정렬됩니다.

요청 Request Parameters:
선택 사항) 특정 작성자의 이름을 필터링 조건으로 사용합니다.
응답 Response Body:

  {
    "id": 1,
    "title": "첫 번째 일정",
    "content": "내용입니다",
    "author": "홍길동",
    "createdAt": "2025-07-29T10:00:00",
    "modifiedAt": "2025-07-29T11:00:00"
  },
  {
    "id": 2,
    "title": "두 번째 일정",
    "content": "다른 내용입니다",
    "author": "김철수",
    "createdAt": "2025-07-29T09:00:00",
    "modifiedAt": "2025-07-29T09:00:00"
  }
  
3. 특정 일정 조회
: 특정 ID를 가진 일정을 조회합니다.

요청 Path Parameter:
{
  "id": 1,
  "title": "첫 번째 일정",
  "content": "내용입니다",
  "author": "홍길동",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T11:00:00"
}

4. 일정 수정
: 특정 ID의 일정을 수정합니다. 수정 시 비밀번호 확인이 필요합니다.

요청 Path Parameter:
id: 수정할 일정의 고유 ID.

요청 Request Body:
{
  "title": "새로운 제목",
  "author": "새로운 작성자",
  "password": "비밀번호"
}


{
  "id": 1,
  "title": "새로운 제목",
  "content": "내용입니다", // Content는 수정 요청에 없으므로 원래 내용 유지
  "author": "새로운 작성자",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T11:05:00" // 수정된 시간으로 업데이트
}

5. 일정 삭제
: 특정 ID의 일정을 삭제합니다. 삭제 시 비밀번호 확인이 필요합니다.

id: 삭제할 일정의 고유 ID.
요청 Request Body:
{
  "password": "비밀번호"
}


ERD 
<img width="1782" height="856" alt="image" src="https://github.com/user-attachments/assets/a9989c94-46e9-4a6c-b200-ec77a81d9c48" />
