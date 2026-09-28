# 📱 Practical 4 – Android Alarm Application using Service & BroadcastReceiver

## 📌 Aim

**Create an Android Alarm application by using Service & BroadcastReceiver.**

This practical is developed as part of the **Mobile Application Development (MAD)** course. The application demonstrates how Android **Service**, **BroadcastReceiver**, and **AlarmManager** can be used to schedule and trigger an alarm.

---

## 🎯 Objectives

The main objectives of this practical are:

* Understand the concept of **Android Service**.
* Understand the concept of **BroadcastReceiver**.
* Implement an **Alarm Application** in Android.
* Schedule an alarm using **AlarmManager**.
* Trigger an alarm when the scheduled time is reached.
* Handle alarm events using **BroadcastReceiver**.
* Perform alarm-related tasks using **Service**.
* Understand background processing in Android.

---

## 🛠️ Technologies Used

* **IDE:** Android Studio
* **Language:** Kotlin
* **UI:** XML
* **Platform:** Android
* **Build System:** Gradle
* **Components:** Service, BroadcastReceiver, AlarmManager

---

# ⏰ What is an Android Alarm?

An **Android Alarm** is a scheduled event that allows an application to perform a particular action at a specified time.

Android provides the **AlarmManager** class to schedule alarms. When the scheduled time is reached, Android can send a broadcast that can be received by a **BroadcastReceiver**.

The BroadcastReceiver can then start a **Service** to perform the required alarm-related task.

---

# 1️⃣ AlarmManager

**AlarmManager** is an Android system service used to schedule actions at a specific time.

It allows the application to tell Android when an alarm should be triggered.

### Working

```text
User Selects Time
       ↓
AlarmManager
       ↓
Alarm Scheduled
       ↓
Specified Time Reached
       ↓
Broadcast Sent
```

---

# 2️⃣ BroadcastReceiver

A **BroadcastReceiver** is an Android component that receives broadcast messages from the Android system or other applications.

In this practical, the BroadcastReceiver receives the broadcast when the scheduled alarm time is reached.

### Working

```text
AlarmManager
      ↓
Broadcast
      ↓
BroadcastReceiver
      ↓
Alarm Action
```

The BroadcastReceiver acts as a connection between the scheduled alarm and the application.

---

# 3️⃣ Service

A **Service** is an Android component that can perform operations in the background without requiring a user interface.

In this practical, the Service is used to handle the alarm-related operation after the BroadcastReceiver receives the alarm broadcast.

### Working

```text
BroadcastReceiver
       ↓
     Service
       ↓
Alarm Operation
```

---

# 🔄 Overall Working of Application

The complete working of the Alarm Application can be represented as:

```text
User
  ↓
Select Alarm Time
  ↓
AlarmManager
  ↓
Alarm Scheduled
  ↓
Scheduled Time Reached
  ↓
BroadcastReceiver
  ↓
Service
  ↓
Alarm / Notification
```

---

# 📱 Application Demonstration

The application demonstrates how Android components work together to create an alarm system.

### Alarm Setting

The user selects the required time and sets the alarm.

```text
User
  ↓
Select Time
  ↓
Set Alarm
  ↓
AlarmManager
```

### Alarm Trigger

When the selected time is reached:

```text
AlarmManager
     ↓
BroadcastReceiver
     ↓
Service
     ↓
Alarm Triggered
```

---

# 🔗 Role of Android Components

| **Component**         | **Purpose**                                       |
| --------------------- | ------------------------------------------------- |
| **AlarmManager**      | Schedules the alarm                               |
| **BroadcastReceiver** | Receives the alarm broadcast                      |
| **Service**           | Performs the alarm operation in the background    |
| **Activity**          | Provides the user interface                       |
| **Intent**            | Used for communication between Android components |

---

# 🔄 Difference Between Service and BroadcastReceiver

| **Feature**            | **Service**                    | **BroadcastReceiver**                  |
| ---------------------- | ------------------------------ | -------------------------------------- |
| Purpose                | Performs background operations | Receives broadcast events              |
| Runs in background     | Yes                            | Triggered when a broadcast is received |
| User Interface         | No                             | No                                     |
| Used in this practical | Handles alarm operation        | Receives alarm event                   |
| Example                | Playing alarm sound            | Receiving scheduled alarm              |

---

# 📂 Project Structure

```text
24012021039_Pr_4_MAD/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── mipmap/
│           │   ├── values/
│           │   └── layout/
│           │       └── ...
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

The repository contains the Android application module and standard Gradle project files.

---

# ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/jiyapatel02/24012021039_Pr_4_MAD.git
```

2. Open **Android Studio**.
3. Select **Open** and choose the cloned project folder.
4. Allow Gradle to sync.
5. Connect an Android device or start an Android Emulator.
6. Click the **Run ▶** button.
7. Set an alarm for the required time.
8. Wait until the scheduled time to test the alarm functionality.

---

# 🧪 Practical Testing

## Test 1 – Set Alarm

**Action:** Select a time and set the alarm.

**Expected Result:**

```text
User
  ↓
Selected Time
  ↓
Alarm Scheduled Successfully
```

The alarm is scheduled for the selected time.

---

## Test 2 – Alarm Trigger

**Action:** Wait until the scheduled alarm time.

**Expected Result:**

```text
Scheduled Time
      ↓
BroadcastReceiver
      ↓
Service
      ↓
Alarm Triggered
```

The application performs the configured alarm operation.

---

## Test 3 – Background Operation

**Action:** Set an alarm and move the application to the background.

**Expected Result:**

The scheduled alarm event is handled using Android background components.

---

# 📚 Concepts Covered

| **No.** | **Concept**             |
| ------- | ----------------------- |
| 1       | Android Service         |
| 2       | BroadcastReceiver       |
| 3       | AlarmManager            |
| 4       | Alarm Scheduling        |
| 5       | Background Processing   |
| 6       | Intent                  |
| 7       | Android Components      |
| 8       | Broadcast Communication |
| 9       | Service Communication   |
| 10      | Alarm Application       |

---

# 🎓 Learning Outcome

After completing this practical, we understand:

* What an Android **Service** is.
* What a **BroadcastReceiver** is.
* How **AlarmManager** is used to schedule alarms.
* How Android broadcasts an event when an alarm is triggered.
* How BroadcastReceiver receives and handles the alarm event.
* How a Service can perform operations in the background.
* How different Android components work together.
* How to create a basic Android Alarm Application.

---

# 👩‍💻 Author

**Jiya Patel**

**B.Tech – Information Technology**

**Mobile Application Development (MAD)**

---

## 🔗 GitHub Repository

[24012021039_Pr_4_MAD – GitHub Repository](https://github.com/jiyapatel02/24012021039_Pr_4_MAD)

---

## ⭐ Conclusion

This practical provides an understanding of **Service, BroadcastReceiver, and AlarmManager** in Android.

By developing the Alarm Application, we learn how Android can schedule an event and handle it in the background using **AlarmManager, BroadcastReceiver, and Service**.

The practical demonstrates the use of Android background components to implement a basic alarm functionality.
