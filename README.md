#ResourcePool Code conventions
------
##0- IDE
 * Do not use CTRL+SHIFT+F or any reformating tool. Rather use indent on small portions of code.
 * Use 2-2-4 space indents everywhere (java, js, xml, html…).

##1- Copyright/Licence
ResourcePool.io is distributed under the Apache2.0 License.
Every single source file **must** include the following headers:  

*HTML (HTML, HTML5)*
```html
<!--
 Copyright 2014 ResourcePool.io

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->
```
```html
<!--
 Created by FirstName LastName on ${DATE}.
-->
 ```
*Java (Class, Enum, Interface), Javascript*
```java
/*
 Copyright 2014 ResourcePool.io

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
```
```java
/**
 * Created by FirstName LastName on ${DATE}.
 */
 ```

##2- Attributes/Variables/Class names
 * Attributes:
camelCase
thisIsASampleVariable
uuid/slug


 * Boolean attributes:
isActivated
hasStarted

 * Prefer primitive types rather than objects (to avoid boxing)


 * Classnames:
MyClassExample
MyUuid
MyDao


##3- Comments

Always follow the comment by a space and a capital letter if not in sentence
```java
// This is a comment
```
For block comments, always have a **line feed** before and after
```java
/*
This is a block comment
*/
```

##4- Javadoc

All Service layer methods should be documented properly
@author
@param
@return
@throws
Example:
```java
/**
   * Checks whether a user's personal data is valid or not
   * @param user containing the data to be checked
   * @return true if valid, false otherwise
   * @throws NullPointerException if user is null
   * @author Loic Ortola
   */
  public boolean isValidPersonalData(User user){
…
```

All Webservice layer methods should be documented properly
@author
@param
@return 
Example:
```java
/**
   * Updates the user's password
   * @param updatedUser the updated user to persist
   * Requires Session-Token and Portal request headers
   * @return 401-UNAUTHORIZED if token is invalid or expired
   * @return 403-FORBIDDEN if user doesn't exist
   * @return 200-OK the updated user
   * @author Loic Ortola
   */
@RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<User> signUp(@RequestBody User newUser) {
...
```

##5- WebService endpoints
Should use the HTTP RequestMethods: 
Example:
User:
mapping /user
GET -> retrieve
POST -> update
PUT -> add
DELETE -> delete

Should have an URI format (no camelcase)
example:
/user/signup
/user/reset_password


##6- Classes

Following structure should be used and visible
```java
  /*=========================================*/
  /*------------ATTRIBUTES-------------------*/
  /*=========================================*/

  /*=========================================*/
  /*------------CONSTRUCTORS-----------------*/
  /*=========================================*/

  /*=========================================*/
  /*------------OVERRIDES--------------------*/
  /*=========================================*/
  
  /*=========================================*/
  /*------------BUILDER----------------------*/
  /*=========================================*/

  /*=========================================*/
  /*------------GETTER/SETTER----------------*/
  /*=========================================*/

  /*=========================================*/
  /*------------CODE-------------------------*/
  /*=========================================*/

  /*=========================================*/
  /*------------PRIVATE CODE-----------------*/
  /*=========================================*/
```

##7- Model Classes
For **every** model class generated, we require:
 * **private** attributes
 * only default constructor, but declare a builder!
 * meaningful toString + hashCode + equals overrides
 * getters/setters: getUser, setUser, isActivated, setActivated, hasStarted, setStarted

Chosen builder pattern:
```java
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private User user;

    private Builder() {
      user = new User();
    }
    public Builder id(Long id) {
      user.id = id;
      return this;
    }
    public Builder firstName(String firstName) {
      user.firstName = firstName;
      return this;
    }
    public Builder creationDate(Date creationDate) {
      user.creationDate = creationDate;
      return this;
    }
    public User build() {
      return user;
    }
  }
```



