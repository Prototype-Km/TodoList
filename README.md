## 📘 API 명세

| 기능         | Method | URL                   | Request Body                                                                 | Response 예시                                                              | 상태코드 |
|--------------|--------|------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------|----------|
| 일정 생성     | POST   | `/api/todo-list`       | `{ "writer": "홍길동", "password": "1234", "contents": "할 일 내용" }`        | `{ "id": 1, "writer": "홍길동", "contents": "할 일 내용", "createdDate": "..." }` | 200 OK   |
| 일정 전체 조회 | GET    | `/api/todo-list`       | -                                                                            | `[{"id":1, "writer":"홍길동", "contents":"운동하기", "createdDate":"..."}, ...]` | 200 OK   |
| 일정 상세 조회 | GET    | `/api/todo-list/{id}`  | -                                                                            | `{ "id": 1, "writer": "홍길동", "contents": "운동하기", "createdDate": "..." }` | 200 OK   |
| 일정 수정     | PATCH  | `/api/todo-list/{id}`  | `{ "writer": "수정자", "password": "1234", "contents": "수정된 내용" }`       | `{ "id": 1, "writer": "수정자", "contents": "수정된 내용", "createdDate": "..." }` | 200 OK   |
| 일정 삭제     | DELETE | `/api/todo-list/{id}`  | `{ "password": "1234" }`                                                     | -                                                                           | 200 OK   |

--- 

## ERD 
![TodoList](https://github.com/user-attachments/assets/30e46a4a-ab96-4347-a653-c380db32642a)

---
