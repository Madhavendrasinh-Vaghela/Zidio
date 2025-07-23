
# 📘 Zidio Connect - API Documentation

This document outlines the key REST API endpoints available in Zidio Connect, covering authentication, student, recruiter, admin, and payment functionalities.

---

## 🔐 1. Authentication APIs

### POST `/api/auth/register`
Registers a new user (Student/Recruiter/Admin).

**Body (JSON):**
```json
{
  "email": "user@example.com",
  "password": "secure123",
  "role": "STUDENT"
}
```

### POST `/api/auth/login`
Logs in and returns a JWT token.

**Body (JSON):**
```json
{
  "email": "user@example.com",
  "password": "secure123"
}
```

**Response:**
```json
{
  "token": "jwt_token_here"
}
```

---

## 👨‍🎓 2. Student APIs

### GET `/api/student/profile/{id}`
Get student profile by ID.

### PUT `/api/student/update`
Update student profile.

### GET `/api/student/jobs`
Fetch all available jobs.

### POST `/api/student/apply/{jobId}`
Apply for a job.

### GET `/api/student/applications`
Get list of applied jobs.

### POST `/api/upload/resume`
Upload resume file to Cloudinary.

**Form Data:**
```
file: [resume.pdf]
```

---

## 💼 3. Recruiter APIs

### POST `/api/recruiter/post-job`
Post a new job listing.

**Body (JSON):**
```json
{
  "title": "Backend Developer",
  "description": "Spring Boot API development",
  "location": "Remote",
  "type": "Internship"
}
```

### GET `/api/recruiter/jobs`
Get jobs posted by recruiter.

### GET `/api/recruiter/applicants/{jobId}`
Get list of student applicants for a job.

---

## 🧑‍💼 4. Admin APIs

### POST `/api/admin/login`
Login for admin account.

### GET `/api/admin/students`
Get all students.

### GET `/api/admin/recruiters`
Get all recruiters.

### GET `/api/admin/jobs`
Get all jobs posted.

### DELETE `/api/admin/student/{id}`
Delete a student by ID.

### DELETE `/api/admin/recruiter/{id}`
Delete a recruiter by ID.

### DELETE `/api/admin/job/{id}`
Delete a job by ID.

---

## 💳 5. Payment APIs

### POST `/api/payment/pay`
Mock payment for students.

**Query Param:**
`amount=499.0`

### GET `/api/payment/my`
Get payment history of logged-in student.

### GET `/api/payment/all`
(Admin Only) Get all payment transactions.

### GET `/api/payment/create-razorpay-order?amount=999.0`
Creates a Razorpay order to initiate real payment.

**Response:**
```json
{
  "id": "order_HK93df02lq",
  "amount": 99900,
  "currency": "INR",
  "status": "created"
}
```

---

## 🔐 Auth Headers

Add this to all protected routes:

```
Authorization: Bearer <your-jwt-token>
```

---

## ✅ Status
All major modules implemented. JWT-secured. Ready for frontend connection.
