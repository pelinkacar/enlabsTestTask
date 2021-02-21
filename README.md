#Test Task Information

#### In order to run the project:
* Java, JDK 8+ should be installed
* maven should be installed.

###Task divided into two parts as:

* 1st - Registration Page
* 2nd - Game Search

### 1- Registration Page Test
You will find 12 test methods inside "Task1RegistrationPageTest" class.

I tried to cover both negative and positive test scenarios in detailed while creating the methods.
You can run them one by one or all together. (If you want to execute them from terminal please refer "3- Execute all test cases in Terminal" section)

You will not run just two of them, because they were required e-mail integration, and I didn't try to integrate for now.
It can easily integrate.
* sign_up_with_wrong_pin_number_at_third_step_registration_form
* sign_up_with_valid_info

All required data for methods are coming from "utility" package -> "Utils" class.
If you need to change any value in the file, it will reflect to the related methods.

After all methods execution, you can find screenshots from the last view inside "screenshots" folder according to name of method.

### 2- Game Search Test
You will find one test method inside "Task2GameSearchTest" class.

I tried to cover test scenario which you defined me specifically.

You can run them inside class. (If you want to execute it from terminal please refer "3- Execute all test cases in Terminal" section)

All required data for methods are coming from "utility" package -> "Utils" class.
If you need to change any value in the file, it will reflect the related methods.

After method execution, you can find screenshot from the last view inside "screenshots" folder according to name of method.

### 3- Execute all test cases in Terminal
Go to terminal:
- `mvn clean` : to clean the code
- `mvn compile` : to check the syntax error
- `mvn test` : to test code

!!!! ATTENTION ⇒ to execute just ONE test class command is given below:
- `mvn -Dtest = Task2GameSearchTest test`




