JShell (REPL - Read-Eval-Print Loop):

Interactive Java: JShell provides an interactive command-line tool for executing Java code snippets directly. This is incredibly useful for quickly testing ideas, learning the language, and prototyping without the need to create a full class, compile, and run.


Microsoft Windows [Version 10.0.26100.4652]
(c) Microsoft Corporation. All rights reserved.

C:\Users\NayeemJohnY>jshell
|  Welcome to JShell -- Version 21.0.7
|  For an introduction type: /help intro

jshell> 2 + 2
$1 ==> 4

jshell> int x = 10
x ==> 10

jshell> x * 5
$3 ==> 50

jshell> System.out.println("Hello JShell")
Hello JShell

jshell> System.out.println("Hello JShell");
Hello JShell

jshell> void greet(String name){
   ...>     System.out.println("Hello " + name);
   ...> }
|  created method greet(String)

jshell> geet("Jav 9")
|  Error:
|  cannot find symbol
|    symbol:   method geet(java.lang.String)
|  geet("Jav 9")
|  ^--^

jshell> greet("Java 9")
Hello Java 9

jshell> for (int i = 0; i < 5; i++){
   ...>     System.out.println("Hello " + i);
   ...> }
Hello 0
Hello 1
Hello 2
Hello 3
Hello 4

jshell> import java.util.ArrayList;

jshell> ArrayList<String> names = new ArrayList<>();
names ==> []

jshell> names.add("Alice")
$11 ==> true

jshell> name.add(12)
|  Error:
|  cannot find symbol
|    symbol:   variable name
|  name.add(12)
|  ^--^

jshell> names.add(12)
|  Error:
|  incompatible types: int cannot be converted to java.lang.String
|  names.add(12)
|            ^^

jshell> names.add("John)
|  Error:
|  unclosed string literal
|  names.add("John)
|            ^

jshell> names.add("John")
$12 ==> true

jshell> names
names ==> [Alice, John]

jshell> /list

   1 : 2 + 2
   2 : int x = 10;
   3 : x * 5
   4 : System.out.println("Hello JShell")
   5 : System.out.println("Hello JShell");
   6 : void greet(String name){
           System.out.println("Hello " + name);
       }
   7 : greet("Java 9")
   8 : for (int i = 0; i < 5; i++){
           System.out.println("Hello " + i);
       }
   9 : import java.util.ArrayList;
  10 : ArrayList<String> names = new ArrayList<>();
  11 : names.add("Alice")
  12 : names.add("John")
  13 : names

jshell> /vars
|    int $1 = 4
|    int x = 10
|    int $3 = 50
|    ArrayList<String> names = [Alice, John]
|    boolean $11 = true
|    boolean $12 = true

jshell> /methods
|    void greet(String)

jshell>