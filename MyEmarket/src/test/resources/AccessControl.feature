Feature: Access Control

  @security
  Scenario Outline: multiuser authentication
    When I attempt to log in using <USR> and <PWD>
    Then My authentication is <isAuthenticated> with role <ROLE>

    Examples: 
      | URL           | USR       | PWD        | ROLE        | isAuthenticated |
      | "http://localhost:8090/login" | "Alice"   | "password" | "DEVELOPER" | true   |
      | "http://localhost:8090/login" | "Alice"   | "invalid"  | "DEVELOPER" | false  |
      | "http://localhost:8090/login" | "Bob"     | "admin"    | "MANAGER"   | true   |
      | "http://localhost:8090/login" | "Bob"     | "invalid"  | "MANAGER"   | false  |
      | "http://localhost:8090/login" | "invalid" | "invalid"  | "MANAGER"   | false  |

  @security
  Scenario Outline: authorization
    Given I am a <ROLE> with username <USR> and password <PWD>
    When I access <URL>
    Then My authorization is <isAuthorized> with role <ROLE>

    Examples: 
      | URL		                   | USR     | PWD | ROLE        | isAuthorized |
      | "/product/"   | "Alice" | "password" | "DEVELOPER" | false        |
      | "/order/"   | "Alice"   | "password" | "DEVELOPER"   | true         |
      | "/product/"   | "Bob" | "admin" | "MANAGER" | true        |
      | "/order/"   | "Bob"   | "admin" | "MANAGER"   | false         |
