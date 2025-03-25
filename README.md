## ✅ TodoList API

## 📘 API 명세 [필수과제]

| 기능         | Method | URL                   | Request Body                                                                 | Response 예시                                                              | 상태코드 |
|--------------|--------|------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------|----------|
| 일정 생성     | POST   | `/api/todo-list`       | `{ "writer": "홍길동", "password": "1234", "contents": "할 일 내용" }`        | `{ "id": 1, "writer": "홍길동", "contents": "할 일 내용", "createdDate": "..." }` | 200 OK   |
| 일정 전체 조회 | GET    | `/api/todo-list`       | -                                                                            | `[{"id":1, "writer":"홍길동", "contents":"운동하기", "createdDate":"..."}, ...]` | 200 OK   |
| 일정 상세 조회 | GET    | `/api/todo-list/{id}`  | -                                                                            | `{ "id": 1, "writer": "홍길동", "contents": "운동하기", "createdDate": "..." }` | 200 OK   |
| 일정 수정     | PATCH  | `/api/todo-list/{id}`  | `{ "writer": "수정자", "password": "1234", "contents": "수정된 내용" }`       | `{ "id": 1, "writer": "수정자", "contents": "수정된 내용", "createdDate": "..." }` | 200 OK   |
| 일정 삭제     | DELETE | `/api/todo-list/{id}`  | `{ "password": "1234" }`                                                     | -                                                                           | 200 OK   |

# 📘 TodoList & User API 명세서 [ 도전과제]

| HTTP Method | URL | 설명 | 요청 데이터 | 응답 |
|-------------|-----|------|-------------|------|
| `POST` | `/api/todo-list2` | 일정 등록 (로그인 필수) | `TodoListRequestDTO2`<br>└ `contents`, `userPassword` | `200 OK`<br>`TodoListResponseDTO2` |
| `GET` | `/api/todo-list2` | 전체 일정 조회 | 없음 | `200 OK`<br>`List<TodoListResponseDTO2>` |
| `GET` | `/api/todo-list2/user/{userId}` | 특정 유저의 전체 일정 조회 | `userId` (Path) | `200 OK`<br>`List<TodoListResponseDTO2>` |
| `GET` | `/api/todo-list2/{id}` | 일정 상세 조회 | `id` (Path) | `200 OK`<br>`TodoListResponseDTO2` |
| `PATCH` | `/api/todo-list2/todo/{id}` | 일정 수정 (비밀번호 필요) | `id` (Path), `TodoListRequestDTO2`<br>└ `contents`, `userPassword` | `200 OK`<br>`TodoListResponseDTO2` |
| `DELETE` | `/api/todo-list2/{id}` | 일정 삭제 (비밀번호 필요) | `id` (Path), `TodoListRequestDTO2`<br>└ `userPassword` | `200 OK` |
| `POST` | `/api/todo-list2/page` | 조건 검색 + 페이징 | `TodoListPageRequestDTO`<br>└ `page`, `size`, `userName`, `contents` | `200 OK`<br>`PagingResponseDTO<TodoListResponseDTO2>` |

---

## ✅ User API

| HTTP Method | URL | 설명 | 요청 데이터 | 응답 |
|-------------|-----|------|-------------|------|
| `POST` | `/api/users/join` | 회원 가입 | `UserRequestDTO`<br>└ `userName`, `userEmail`, `userPassword` | `200 OK`<br>`UserResponseDTO` |
| `POST` | `/api/users/login` | 로그인 (세션 저장) | `UserRequestDTO`<br>└ `userEmail`, `userPassword` | `200 OK`<br>`UserResponseDTO` |
| `GET` | `/api/users` | 회원 전체 조회 | 없음 | `200 OK`<br>`List<UserResponseDTO>` |

---

## ⚙️ 기타 정보

- **세션 기반 인증**  
  `loginUser` 세션 키로 로그인 유저 정보 확인 후 일정 등록/수정/삭제 가능.

- **유효성 검사**  
  `@Valid`, `@NotBlank`, `@Size` 어노테이션으로 DTO에서 처리.

- **예외 처리**  
  - 비밀번호 불일치: `401 Unauthorized`  
  - 없는 일정 or 유저: `404 Not Found`  
  - 서버 내부 오류: `500 Internal Server Error`


## ERD 
![TodoList](https://github.com/user-attachments/assets/30e46a4a-ab96-4347-a653-c380db32642a)

---

## ERD 도전과제 

<img width="315" alt="스크린샷 2025-03-25 20 37 18" src="https://github.com/user-attachments/assets/e9abdd8e-4165-45d1-9cba-2ff55a06eee3" />

