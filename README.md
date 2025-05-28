# oovp_ManagementHotel_p2H



# Hotel Management System
Object Oriented Visual Programming 

This Java-based Hotel Management System is a desktop application designed to handle room data, bookings, check-in, and check-out processes efficiently. Built with Java Swing (GUI) and MySQL, the system includes multiple functional modules for administrators to manage hotel operations smoothly.

## 🔧 Technologies Used

- Java (Swing for GUI)
- MySQL (JDBC for database connectivity)
- iText (for PDF receipt generation)

---

## 📁 Application Modules & Features

### 1. **Room Management**
> _Manages all hotel room records._

**Features:**
- Add new rooms with specified type, bed option, and price.
- Edit existing room information.
- Delete rooms if needed.
- Displays a table listing all rooms with:
  - Room Number
  - Room Type (e.g., Suite, Deluxe, Standard)
  - Bed Type (Single/Double)
  - Availability Status (`available` / `booked`)
  - Price formatted in local currency (e.g., Rp. 450.000,-)

📝 **Note:**  
When a room is added, its availability is automatically set to `available`.

---

### 2. **Booking / Check-In Form**
> _Handles the customer check-in and room reservation process._

**Features:**
- Input customer data:
  - Name, NIK (ID), Phone Number, Email
- Select booking details:
  - Check-In & Check-Out dates
  - Room Type, Bed Type
  - Available Room Number (only rooms marked as `available`)
  - Payment Method (e.g., UPC)
- **Total price is calculated automatically**:
  - Price per night × number of nights

📝 **Note:**  
After booking, the room status is automatically updated to `booked`.

---

### 3. **Booking Confirmation**
> _Ensures room and booking data are correctly stored._

- Displays the total price in red for emphasis.
- Ensures booking data is inserted into both `customers` and `bookings` tables.
- Automatically sets the room status to `booked`.

---

### 4. **Check-Out Module**
> _Retrieves customer and room booking details by room number._

**Features:**
- Admin enters a **room number** in the search field.
- The system **fetches all related data** for:
  - Customer
  - Room
  - Booking dates
- Ensures the room is currently marked as `booked`.

---

### 5. **Print Receipt & Checkout**
> _Completes the transaction and updates records._

**Features:**
- Upon clicking the **Print Receipt** button:
  - ✅ The room’s availability status is automatically updated to `available`.
  - 📄 A **PDF receipt is generated**, containing:
    - Customer data (Name, NIK, Contact)
    - Room booking details (Room No, Type, Dates, Total Price)
    - **Timestamp** of receipt generation (current date & time)

📝 **Note:**  
The generated PDF acts as official proof of payment.

---

## 📌 Workflow Summary

| Action                 | Automatic Effect                                         
|------------------------|---------------------------------------------------------
| Add Room               | Room marked as `available`                              
| Book Room              | Room marked as `booked`                                 
| Check-Out & Print PDF  | Room reverted to `available`, PDF receipt is generated 

---

## 📎 Future Enhancements (Optional Ideas)

- Search & filter rooms by price, type, or availability.
- Email PDF receipt to customer automatically.
- Implement login system for admin access control.
- Add payment verification or integration.

---

## 📬 Author

> Developed by: **@anjelin.ln**   
