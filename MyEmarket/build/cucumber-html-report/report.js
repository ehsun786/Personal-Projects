$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AccessControl.feature");
formatter.feature({
  "line": 1,
  "name": "Access Control",
  "description": "",
  "id": "access-control",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "multiuser authentication",
  "description": "",
  "id": "access-control;multiuser-authentication",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@security"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I attempt to log in using \u003cUSR\u003e and \u003cPWD\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "My authentication is \u003cisAuthenticated\u003e with role \u003cROLE\u003e",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "access-control;multiuser-authentication;",
  "rows": [
    {
      "cells": [
        "URL",
        "USR",
        "PWD",
        "ROLE",
        "isAuthenticated"
      ],
      "line": 9,
      "id": "access-control;multiuser-authentication;;1"
    },
    {
      "cells": [
        "\"http://localhost:8090/login\"",
        "\"Alice\"",
        "\"password\"",
        "\"DEVELOPER\"",
        "true"
      ],
      "line": 10,
      "id": "access-control;multiuser-authentication;;2"
    },
    {
      "cells": [
        "\"http://localhost:8090/login\"",
        "\"Alice\"",
        "\"invalid\"",
        "\"DEVELOPER\"",
        "false"
      ],
      "line": 11,
      "id": "access-control;multiuser-authentication;;3"
    },
    {
      "cells": [
        "\"http://localhost:8090/login\"",
        "\"Bob\"",
        "\"admin\"",
        "\"MANAGER\"",
        "true"
      ],
      "line": 12,
      "id": "access-control;multiuser-authentication;;4"
    },
    {
      "cells": [
        "\"http://localhost:8090/login\"",
        "\"Bob\"",
        "\"invalid\"",
        "\"MANAGER\"",
        "false"
      ],
      "line": 13,
      "id": "access-control;multiuser-authentication;;5"
    },
    {
      "cells": [
        "\"http://localhost:8090/login\"",
        "\"invalid\"",
        "\"invalid\"",
        "\"MANAGER\"",
        "false"
      ],
      "line": 14,
      "id": "access-control;multiuser-authentication;;6"
    }
  ],
  "keyword": "Examples"
});
formatter.scenarioOutline({
  "line": 17,
  "name": "authorization",
  "description": "",
  "id": "access-control;authorization",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 16,
      "name": "@security"
    }
  ]
});
formatter.step({
  "line": 18,
  "name": "I am a \u003cROLE\u003e with username \u003cUSR\u003e and password \u003cPWD\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "I access \u003cURL\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "My authorization is \u003cisAuthorized\u003e with role \u003cROLE\u003e",
  "keyword": "Then "
});
formatter.examples({
  "line": 22,
  "name": "",
  "description": "",
  "id": "access-control;authorization;",
  "rows": [
    {
      "cells": [
        "URL",
        "USR",
        "PWD",
        "ROLE",
        "isAuthorized"
      ],
      "line": 23,
      "id": "access-control;authorization;;1"
    },
    {
      "cells": [
        "\"/product/\"",
        "\"Alice\"",
        "\"password\"",
        "\"DEVELOPER\"",
        "false"
      ],
      "line": 24,
      "id": "access-control;authorization;;2"
    },
    {
      "cells": [
        "\"/order/\"",
        "\"Alice\"",
        "\"password\"",
        "\"DEVELOPER\"",
        "true"
      ],
      "line": 25,
      "id": "access-control;authorization;;3"
    },
    {
      "cells": [
        "\"/product/\"",
        "\"Bob\"",
        "\"admin\"",
        "\"MANAGER\"",
        "true"
      ],
      "line": 26,
      "id": "access-control;authorization;;4"
    },
    {
      "cells": [
        "\"/order/\"",
        "\"Bob\"",
        "\"admin\"",
        "\"MANAGER\"",
        "false"
      ],
      "line": 27,
      "id": "access-control;authorization;;5"
    }
  ],
  "keyword": "Examples"
});
formatter.uri("OrderPersistence.feature");
formatter.feature({
  "line": 1,
  "name": "Order.Persistence",
  "description": "I want to persist orders for accounting purposes",
  "id": "order.persistence",
  "keyword": "Feature"
});
formatter.uri("ProductPersistence.feature");
formatter.feature({
  "line": 1,
  "name": "Product.Persistence",
  "description": "I want to create and manage products for my catalogue",
  "id": "product.persistence",
  "keyword": "Feature"
});
});