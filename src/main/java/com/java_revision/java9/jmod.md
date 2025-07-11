(Conceptual) Java Platform Module System (JPMS) / Project Jigsaw
This is the biggest feature, but a "quick hands-on" is challenging without building a multi-module project. Instead, let's observe its impact and how you interact with it.

Understanding the Concepts:

Modules: Think of them as self-contained units with explicitly declared dependencies and exported packages.

module-info.java: This file defines a module. It goes in the root of your module's source directory.

requires: Declares a dependency on another module.

exports: Specifies which packages are visible to other modules.

jlink: Tool to create custom runtime images.

Quick Observation:

JDK Modularity: Open your terminal and navigate to your Java 9+ JDK installation directory. Go into the jmods folder.

cd /path/to/your/jdk-9+/jmods (or similar, depending on your OS)

List the contents: ls (or dir on Windows)

Hint: You'll see .jmod files like java.base.jmod, java.desktop.jmod, java.sql.jmod, etc. These are the modularized JDK modules.

Using jlink (Conceptual):
While we won't build a full jlink example, understand its purpose:

Imagine you have a simple "Hello World" application that only uses java.base.

With jlink, you could create a runtime environment that only contains java.base and your application's modules, resulting in a much smaller distribution than shipping the entire JRE.

What you learned (conceptually): The JDK itself is modular. The module system allows you to define boundaries for your code, leading to more reliable, secure, and compact applications. While a full demo requires more setup, understanding module-info.java and the requires/exports keywords is key.

Here’s a complete working solution for the code task:

> **🧠 Code Task:** Create two modules:
>
> * `greeting.service` → defines a `GreetingService` interface & its implementation
> * `greeting.app` → consumes the `GreetingService` and runs the app

---

## ✅ Folder Structure

```
java9-modules/
├── greeting.service/
│   ├── module-info.java
│   └── com/example/service/GreetingService.java
│   └── com/example/service/impl/GreetingServiceImpl.java
└── greeting.app/
    ├── module-info.java
    └── com/example/app/MainApp.java
```

---

## 1️⃣ `greeting.service/module-info.java`

```java
module greeting.service {
    exports com.example.service;
}
```

---

## 2️⃣ `GreetingService.java`

```java
package com.example.service;

public interface GreetingService {
    String greet(String name);
}
```

---

## 3️⃣ `GreetingServiceImpl.java`

```java
package com.example.service.impl;

import com.example.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
```

---

## 4️⃣ `greeting.app/module-info.java`

```java
module greeting.app {
    requires greeting.service;
}
```

---

## 5️⃣ `MainApp.java`

```java
package com.example.app;

import com.example.service.GreetingService;
import com.example.service.impl.GreetingServiceImpl;

public class MainApp {
    public static void main(String[] args) {
        GreetingService service = new GreetingServiceImpl();
        System.out.println(service.greet("Nayeem"));
    }
}
```

---

## ✅ How to Compile and Run with Modules

### 1. Compile both modules:

```bash
javac -d out/greeting.service \
      greeting.service/module-info.java \
      greeting.service/com/example/service/*.java \
      greeting.service/com/example/service/impl/*.java

javac --module-path out -d out/greeting.app \
      greeting.app/module-info.java \
      greeting.app/com/example/app/MainApp.java
```

### 2. Run the application:

```bash
java --module-path out -m greeting.app/com.example.app.MainApp
```
