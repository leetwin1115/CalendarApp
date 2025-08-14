일정 관리 API 명세서
본 문서는 간단한 일정 관리를 위한 백엔드 API의 명세와 주요 리팩토링 과정을 설명합니다.

기본 URL: /api/calendars

기존 코드 주요 리팩토링 및 개선 사항
develop 패키지는 calenderApp코드의 중복을 제거하고 다음과 같은 리팩토링을 진행하여 과제를 진행했습니다.

1. 공통 필드 분리를 통한 중복 제거 (BaseEntity)
문제점: User와 Calendar 등 모든 엔티티에 생성시간(createdAt), 수정시간(modifiedAt) 필드와 관련 어노테이션(@EntityListeners, @CreatedDate, @LastModifiedDate)이 반복적으로 사용되고 있었습니다.

개선 방안: **@MappedSuperclass**를 사용한 BaseEntity를 생성하여 공통 필드와 JPA Auditing 설정을 한 곳에서 관리하도록 개선했습니다.

2. Repository 로직 개선
문제점: 기존 Repository에 중복되는 @Repository 어노테ㅔ이션이 존재했습니다.

개선 방안: @Repository 어노테이션을 CalendarRequest클래스와 CalendarUpdateRequest 클래스에서 제거했습니다.


API 목록
1. 일정 생성
새로운 일정을 생성합니다.

Method: POST

URL: /api/calendars

Request Body
JSON

{
  "title": "할 일 제목",
  "content": "상세 내용",
  "author": "작성자 이름",
  "password": "비밀번호"
}
Success Response (201 Created)
JSON

{
  "id": 1,
  "title": "할 일 제목",
  "content": "상세 내용",
  "author": "작성자 이름",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T10:00:00"
}

2. 전체 또는 작성자별 일정 조회
모든 일정을 조회하거나, author 쿼리 파라미터를 통해 특정 작성자의 일정만 필터링하여 조회합니다. 결과는 수정일 기준 내림차순으로 정렬됩니다.

Method: GET

URL: /api/calendars 또는 /api/calendars?author={name}

Request Parameters
author (optional, string): 특정 작성자의 이름을 기준으로 필터링합니다.

Success Response (200 OK)
JSON

[
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
]

3. 특정 일정 조회
고유 ID를 사용하여 특정 일정을 조회합니다.

Method: GET

URL: /api/calendars/{id}

Path Parameter
id (required, long): 조회할 일정의 고유 ID.

Success Response (200 OK)
JSON

{
  "id": 1,
  "title": "첫 번째 일정",
  "content": "내용입니다",
  "author": "홍길동",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T11:00:00"
}

4. 일정 수정 (Update)
특정 ID의 일정을 수정합니다. 수정 시 password를 통한 인증이 필요합니다.

Method: PUT

URL: /api/calendars/{id}

Path Parameter
id (required, long): 수정할 일정의 고유 ID.

Request Body
JSON

{
  "title": "새로운 제목",
  "author": "새로운 작성자",
  "password": "비밀번호"
}
Success Response (200 OK)
JSON

{
  "id": 1,
  "title": "새로운 제목",
  "content": "내용입니다",
  "author": "새로운 작성자",
  "createdAt": "2025-07-29T10:00:00",
  "modifiedAt": "2025-07-29T11:05:00"
}

5. 일정 삭제 (Delete)
특정 ID의 일정을 삭제합니다. 삭제 시 password를 통한 인증이 필요합니다.

Method: DELETE

URL: /api/calendars/{id}

Path Parameter
id (required, long): 삭제할 일정의 고유 ID.

Request Body
JSON

{
  "password": "비밀번호"
}
Success Response (204 No Content)
응답 본문(Body) 없이 성공 상태 코드만 반환합니다.


ERD 다이어그램
<img width="1782" height="856" alt="image" src="https://github.com/user-attachments/assets/a9989c94-46e9-4a6c-b200-ec77a81d9c48" />
