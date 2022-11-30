Uma clínica de estética faz investimentos em marketing digital para captar clientes para realizar procedimentos como o BOTOX, dentre outros. A partir de investimentos em tráfego pago em redes sociais e outras estratégias, chegam VISITANTES (Estágio 1)  no site ou nas redes sociais da clínica. Quando os visitantes se manifestam no sentido de ter acesso à mais informações sobre os serviços, estes passam a ter o status de possíveis interessados (LEAD - estágio 2) nos serviços. Os VISITANTES são o estágio 1 e os LEADS são o estágio 2 no funil de vendas.

Os LEADS são tratados para que venham realizar as avaliações para os procedimentos. Quando os pacientes agendam consultas ou procedimentos eles se tornam oportunidades, as quais vamos denominar como AGENDAS MARCADAS (Estágio 3). Algumas dessas consultas não se concretizam e precisam ser tratadas por quem acompanha o funil de vendas. Quando as consultas são realizadas elas devem ser marcadas no funil de vendas como AGENDAS REALIZADAS (Estágio 4).

As consultas geram orçamentos para serem fechados pelos pacientes.Nesse momento o funil de vendas passa para o estágio 5  (ORÇAMENTO REALIZADOS), que podem ou não ser concretizados. Caso este seja aceito pelo cliente o funil de vendas entra em sua fase final que é ORÇAMENTOS FECHADOS (estágio 6).

Resumindo os estágios do funil de vendas.

Estágio 1: VISITANTES

Estágio 2: LEADS

Estágio 3: AGENDAS MARCADAS 

Estágio 4: AGENDAS REALIZADAS 

Estágio 5: ORÇAMENTO REALIZADOS

Estágio 6. ORÇAMENTOS FECHADOS

{
  "openapi": "3.0.0",
  "info": {
    "title": "home-iot-api",
    "description": "The API for the EatBacon IOT project",
    "version": "1.0.0"
  },
  "servers": [
    {
      "url": "https://virtserver.swaggerhub.com/Cleytonalbu/Clinica/1.0.0",
      "description": "SwaggerHub API Auto Mocking"
    }
  ],
  "paths": {
    "/devices": {
      "get": {
        "tags": [
          "Device"
        ],
        "description": "returns all registered devices",
        "operationId": "getDevices",
        "parameters": [
          {
            "name": "skip",
            "in": "query",
            "description": "number of records to skip",
            "required": false,
            "style": "form",
            "explode": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "limit",
            "in": "query",
            "description": "max number of records to return",
            "required": false,
            "style": "form",
            "explode": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "All the devices",
            "content": {
              "application/json": {
                "schema": {
                  "type": "array",
                  "items": {
                    "type": "string",
                    "format": "uri",
                    "example": "http://10.0.0.225:8080"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Device"
        ],
        "operationId": "register",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/DeviceRegistrationInfo"
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "successfully registered device"
          }
        }
      }
    },
    "/lighting/dimmers/{deviceId}/{value}": {
      "post": {
        "tags": [
          "Z-Wave"
        ],
        "operationId": "setDimmer",
        "parameters": [
          {
            "name": "deviceId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          },
          {
            "name": "value",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "maximum": 100,
              "minimum": 0,
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ApiResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/lighting/dimmers/{deviceId}/{value}/timer/{timeunit}": {
      "post": {
        "tags": [
          "Z-Wave"
        ],
        "description": "sets a dimmer to a specific value on a timer",
        "operationId": "setDimmerTimer",
        "parameters": [
          {
            "name": "deviceId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          },
          {
            "name": "value",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "timeunit",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          },
          {
            "name": "units",
            "in": "query",
            "required": false,
            "style": "form",
            "explode": true,
            "schema": {
              "type": "string",
              "default": "milliseconds",
              "enum": [
                "seconds",
                "minutes",
                "milliseconds"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ApiResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/lighting/switches/{deviceId}": {
      "get": {
        "tags": [
          "Z-Wave"
        ],
        "operationId": "getSwitchState",
        "parameters": [
          {
            "name": "deviceId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/DeviceState"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/lighting/switches/{deviceId}/{value}": {
      "post": {
        "tags": [
          "Z-Wave"
        ],
        "operationId": "setSwitch",
        "parameters": [
          {
            "name": "deviceId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          },
          {
            "name": "value",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string",
              "enum": [
                "true",
                "false"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ApiResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/lighting/switches/{deviceId}/{value}/timer/{minutes}": {
      "post": {
        "tags": [
          "Z-Wave"
        ],
        "description": "sets a switch to a specific value on a timer",
        "operationId": "setSwitchTimer",
        "parameters": [
          {
            "name": "deviceId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          },
          {
            "name": "value",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string",
              "enum": [
                "true",
                "false"
              ]
            }
          },
          {
            "name": "minutes",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "response",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ApiResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/lightingSummary": {
      "get": {
        "tags": [
          "Z-Wave"
        ],
        "operationId": "getLightingSummary",
        "responses": {
          "200": {
            "description": "ok",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/LightingSummary"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "ZWave"
      }
    },
    "/temperature": {
      "get": {
        "tags": [
          "Environment"
        ],
        "operationId": "temperatureSummary",
        "responses": {
          "200": {
            "description": "ok",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/TemperatureSummary"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Environment"
      }
    },
    "/temperature/forecast/{days}": {
      "get": {
        "tags": [
          "Environment"
        ],
        "operationId": "getForecast",
        "parameters": [
          {
            "name": "days",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "the forecast",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ForecastResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Environment"
      }
    },
    "/temperature/{zoneId}": {
      "get": {
        "tags": [
          "Environment"
        ],
        "operationId": "getZoneTemperature",
        "parameters": [
          {
            "name": "zoneId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Zone temperature",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/TemperatueZoneStatus"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Environment"
      }
    },
    "/temperature/{zoneId}/heater": {
      "get": {
        "tags": [
          "Environment"
        ],
        "description": "gets the state of the heater",
        "operationId": "getHeaterState",
        "parameters": [
          {
            "name": "zoneId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "heater state",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/HeaterState"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Environment"
      }
    },
    "/temperature/{zoneId}/heater/{state}": {
      "post": {
        "tags": [
          "Environment"
        ],
        "description": "turns the heater on or off",
        "operationId": "setHeaterState",
        "parameters": [
          {
            "name": "zoneId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string"
            }
          },
          {
            "name": "state",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string",
              "enum": [
                "false",
                "true"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Status of the operation",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/ApiResponse"
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Environment"
      }
    },
    "/zones": {
      "get": {
        "tags": [
          "Zones"
        ],
        "operationId": "getZones",
        "responses": {
          "200": {
            "description": "ok",
            "content": {
              "application/json": {
                "schema": {
                  "type": "array",
                  "items": {
                    "type": "string"
                  }
                }
              }
            }
          }
        },
        "x-swagger-router-controller": "Zones"
      }
    },
    "/zones/{zoneId}/quiet": {
      "get": {
        "tags": [
          "Zones"
        ],
        "operationId": "quietZone",
        "parameters": [
          {
            "name": "zoneId",
            "in": "path",
            "required": true,
            "style": "simple",
            "explode": false,
            "schema": {
              "type": "string",
              "enum": [
                "basement",
                "first-floor",
                "second-floor"
              ]
            }
          }
        ],
        "responses": {
          "200": {
            "description": "ok"
          }
        },
        "x-swagger-router-controller": "Zones"
      }
    }
  },
  "components": {
    "schemas": {
      "LightingSummary": {
        "type": "object",
        "properties": {
          "zones": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/LightingZone"
            }
          },
          "zoneStatus": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/LightingZoneStatus"
            }
          }
        },
        "description": "ok"
      },
      "LightingZone": {
        "type": "object",
        "properties": {
          "id": {
            "type": "string"
          },
          "name": {
            "type": "string"
          },
          "deviceId": {
            "type": "integer",
            "format": "int32"
          },
          "deviceType": {
            "type": "string",
            "enum": [
              "dimmer",
              "switch"
            ]
          },
          "zone": {
            "type": "string"
          }
        }
      },
      "LightingZoneStatus": {
        "type": "object",
        "properties": {
          "id": {
            "type": "string"
          },
          "name": {
            "type": "string"
          },
          "lastUpdate": {
            "type": "string",
            "format": "date-time"
          },
          "level": {
            "type": "integer",
            "format": "int32"
          }
        },
        "description": "the status of the lighting zone."
      },
      "TemperatureSummary": {
        "type": "object",
        "properties": {
          "zones": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/TemperatureZone"
            }
          },
          "zoneStatus": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/TemperatueZoneStatus"
            }
          }
        },
        "description": "ok"
      },
      "TemperatureZone": {
        "required": [
          "id",
          "name"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "description": "the unique identifier for the zone",
            "format": "int32"
          },
          "name": {
            "type": "string"
          },
          "inputPosition": {
            "type": "integer",
            "format": "int32"
          },
          "outputPosition": {
            "type": "integer",
            "format": "int32"
          },
          "zone": {
            "type": "string"
          }
        },
        "description": "a single temperature zone"
      },
      "TemperatueZoneStatus": {
        "required": [
          "id",
          "timestamp",
          "value"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "string",
            "description": "the unique identifier for the zone"
          },
          "name": {
            "type": "string",
            "description": "the name of the zone"
          },
          "value": {
            "type": "number",
            "description": "the temperature in the zone",
            "format": "double"
          },
          "units": {
            "type": "string",
            "description": "the temperature units",
            "default": "fahrenheit",
            "enum": [
              "celsius",
              "fahrenheit"
            ]
          },
          "timestamp": {
            "type": "string",
            "description": "the timestamp when the temperature was measured",
            "format": "date-time"
          }
        },
        "description": "status of a single zone"
      },
      "ApiResponse": {
        "type": "object",
        "properties": {
          "code": {
            "type": "integer",
            "format": "int32"
          },
          "message": {
            "type": "string",
            "example": "everything is ok"
          }
        }
      },
      "HeaterState": {
        "type": "object",
        "properties": {
          "id": {
            "type": "string"
          },
          "state": {
            "type": "string"
          }
        }
      },
      "DeviceState": {
        "type": "object",
        "properties": {
          "id": {
            "type": "string"
          },
          "name": {
            "type": "string"
          },
          "lastUpdate": {
            "type": "string",
            "format": "date-time"
          },
          "level": {
            "type": "integer",
            "format": "int32"
          }
        }
      },
      "ForecastResponse": {
        "type": "object",
        "properties": {
          "city": {
            "$ref": "#/components/schemas/City"
          },
          "values": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Forecast"
            }
          }
        }
      },
      "Forecast": {
        "type": "object",
        "properties": {
          "date": {
            "type": "string",
            "format": "date-time"
          },
          "pressure": {
            "type": "number",
            "format": "double"
          },
          "humidity": {
            "type": "integer",
            "format": "int32"
          },
          "windSpeed": {
            "type": "number",
            "format": "double"
          },
          "clouds": {
            "type": "integer",
            "format": "int32"
          },
          "temperature": {
            "$ref": "#/components/schemas/ForecastTemperature"
          },
          "weather": {
            "$ref": "#/components/schemas/WeatherForecast"
          }
        }
      },
      "City": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "lat": {
            "type": "number",
            "format": "double"
          },
          "lon": {
            "type": "number",
            "format": "double"
          },
          "country": {
            "type": "string"
          }
        }
      },
      "ForecastTemperature": {
        "type": "object",
        "properties": {
          "low": {
            "type": "number",
            "format": "double"
          },
          "high": {
            "type": "number",
            "format": "double"
          },
          "morning": {
            "type": "number",
            "format": "double"
          },
          "day": {
            "type": "number",
            "format": "double"
          },
          "evening": {
            "type": "number",
            "format": "double"
          },
          "night": {
            "type": "number",
            "format": "double"
          }
        }
      },
      "WeatherForecast": {
        "type": "object",
        "properties": {
          "summary": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "icon": {
            "type": "string"
          }
        }
      },
      "DeviceRegistrationInfo": {
        "type": "object",
        "properties": {
          "uri": {
            "type": "string",
            "format": "uri",
            "example": "http://10.0.0.220:8080"
          },
          "id": {
            "type": "string",
            "format": "uuid",
            "example": "0729a580-2240-11e6-9eb5-0002a5d5c51b"
          }
        }
      }
    }
  }
}
