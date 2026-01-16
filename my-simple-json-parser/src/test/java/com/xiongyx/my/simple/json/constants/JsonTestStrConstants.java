package com.xiongyx.my.simple.json.constants;

public class JsonTestStrConstants {

    public static final String json1 = "{\n" +
        "  \"orderId\": \"ORD-2023-78945\",\n" +
        "  \"customer\": {\n" +
        "    \"id\": \"CUST-00123\",\n" +
        "    \"name\": \"张三\",\n" +
        "    \"contact\": {\n" +
        "      \"email\": \"zhangsan@example.com\",\n" +
        "      \"phone\": \"+86-13800138000\",\n" +
        "      \"address\": {\n" +
        "        \"street\": \"朝阳区建国路88号\",\n" +
        "        \"city\": \"北京\",\n" +
        "        \"postalCode\": \"100025\",\n" +
        "        \"country\": \"中国\"\n" +
        "      }\n" +
        "    },\n" +
        "    \"preferences\": {\n" +
        "      \"language\": \"zh-CN\",\n" +
        "      \"currency\": \"CNY\",\n" +
        "      \"notificationSettings\": {\n" +
        "        \"email\": true,\n" +
        "        \"sms\": false,\n" +
        "        \"push\": true\n" +
        "      }\n" +
        "    }\n" +
        "  },\n" +
        "  \"items\": [\n" +
        "    {\n" +
        "      \"sku\": \"PROD-001\",\n" +
        "      \"name\": \"智能手机\",\n" +
        "      \"quantity\": 1,\n" +
        "      \"price\": 2999.99,\n" +
        "      \"discounts\": [\n" +
        "        {\n" +
        "          \"type\": \"seasonal\",\n" +
        "          \"amount\": 200.00\n" +
        "        },\n" +
        "        {\n" +
        "          \"type\": \"coupon\",\n" +
        "          \"code\": \"SAVE50\",\n" +
        "          \"amount\": 50.00\n" +
        "        }\n" +
        "      ],\n" +
        "      \"specifications\": {\n" +
        "        \"color\": \"黑色\",\n" +
        "        \"storage\": \"256GB\",\n" +
        "        \"ram\": \"8GB\"\n" +
        "      }\n" +
        "    },\n" +
        "    {\n" +
        "      \"sku\": \"PROD-045\",\n" +
        "      \"name\": \"无线耳机\",\n" +
        "      \"quantity\": 2,\n" +
        "      \"price\": 399.50,\n" +
        "      \"warranty\": {\n" +
        "        \"duration\": \"24 months\",\n" +
        "        \"type\": \"extended\"\n" +
        "      }\n" +
        "    }\n" +
        "  ],\n" +
        "  \"payment\": {\n" +
        "    \"method\": \"credit_card\",\n" +
        "    \"details\": {\n" +
        "      \"cardType\": \"visa\",\n" +
        "      \"lastFourDigits\": \"1234\",\n" +
        "      \"transactionId\": \"TX-789456123\"\n" +
        "    },\n" +
        "    \"total\": {\n" +
        "      \"subtotal\": 3798.99,\n" +
        "      \"tax\": 379.90,\n" +
        "      \"shipping\": 0.00,\n" +
        "      \"grandTotal\": 4178.89\n" +
        "    }\n" +
        "  },\n" +
        "  \"shipping\": {\n" +
        "    \"method\": \"express\",\n" +
        "    \"trackingNumbers\": [\"EXPRESS-789456123CN\", \"EXPRESS-789456124CN\"],\n" +
        "    \"estimatedDelivery\": \"2023-12-25T14:30:00Z\",\n" +
        "    \"history\": [\n" +
        "      {\n" +
        "        \"timestamp\": \"2023-12-20T09:15:00Z\",\n" +
        "        \"status\": \"已发货\",\n" +
        "        \"location\": \"上海仓库\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"timestamp\": \"2023-12-21T16:45:00Z\",\n" +
        "        \"status\": \"转运中\",\n" +
        "        \"location\": \"北京分拨中心\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  \"metadata\": {\n" +
        "    \"createdAt\": \"2023-12-19T10:30:15Z\",\n" +
        "    \"updatedAt\": \"2023-12-22T08:45:30Z\",\n" +
        "    \"version\": \"2.1\",\n" +
        "    \"tags\": [\"urgent\", \"electronics\", \"gift\"]\n" +
        "  }\n" +
        "}";

    public static final String json2 = "{\n" +
        "  \"company\": {\n" +
        "    \"name\": \"科技先锋有限公司\",\n" +
        "    \"founded\": \"2010-03-15\",\n" +
        "    \"departments\": [\n" +
        "      {\n" +
        "        \"id\": \"DEPT-001\",\n" +
        "        \"name\": \"研发部\",\n" +
        "        \"manager\": {\n" +
        "          \"employeeId\": \"EMP-001\",\n" +
        "          \"name\": \"李四\",\n" +
        "          \"position\": \"技术总监\",\n" +
        "          \"skills\": [\"Java\", \"Python\", \"架构设计\", \"项目管理\"]\n" +
        "        },\n" +
        "        \"teams\": [\n" +
        "          {\n" +
        "            \"teamName\": \"后端开发组\",\n" +
        "            \"members\": [\n" +
        "              {\n" +
        "                \"employeeId\": \"EMP-002\",\n" +
        "                \"name\": \"王五\",\n" +
        "                \"position\": \"高级工程师\",\n" +
        "                \"projects\": [\n" +
        "                  {\n" +
        "                    \"name\": \"微服务架构升级\",\n" +
        "                    \"status\": \"in_progress\",\n" +
        "                    \"technologies\": [\"Spring Boot\", \"Docker\", \"Kubernetes\"]\n" +
        "                  }\n" +
        "                ]\n" +
        "              }\n" +
        "            ]\n" +
        "          }\n" +
        "        ],\n" +
        "        \"budget\": {\n" +
        "          \"annual\": 5000000,\n" +
        "          \"q1\": 1250000,\n" +
        "          \"q2\": 1250000,\n" +
        "          \"q3\": 1250000,\n" +
        "          \"q4\": 1250000\n" +
        "        }\n" +
        "      }\n" +
        "    ],\n" +
        "    \"policies\": {\n" +
        "      \"remoteWork\": true,\n" +
        "      \"flexibleHours\": true,\n" +
        "      \"benefits\": {\n" +
        "        \"healthInsurance\": true,\n" +
        "        \"retirementPlan\": {\n" +
        "          \"type\": \"401k\",\n" +
        "          \"companyMatch\": 0.05\n" +
        "        }\n" +
        "      }\n" +
        "    }\n" +
        "  }\n" +
        "}";

    public static final String json3 = "{\n" +
        "  \"application\": \"DataProcessor\",\n" +
        "  \"config\": {\n" +
        "    \"version\": \"3.2.1\",\n" +
        "    \"environment\": \"production\",\n" +
        "    \"features\": {\n" +
        "      \"analytics\": true,\n" +
        "      \"caching\": {\n" +
        "        \"enabled\": true,\n" +
        "        \"strategy\": \"redis\",\n" +
        "        \"ttl\": 3600,\n" +
        "        \"maxSize\": \"2GB\"\n" +
        "      },\n" +
        "      \"logging\": {\n" +
        "        \"level\": \"INFO\",\n" +
        "        \"targets\": [\"file\", \"elasticsearch\"],\n" +
        "        \"formats\": [\"json\", \"text\"]\n" +
        "      }\n" +
        "    },\n" +
        "    \"connections\": {\n" +
        "      \"database\": {\n" +
        "        \"primary\": {\n" +
        "          \"url\": \"jdbc:mysql://localhost:3306/main_db\",\n" +
        "          \"poolSize\": 20,\n" +
        "          \"timeout\": 30000\n" +
        "        },\n" +
        "        \"replica\": {\n" +
        "          \"url\": \"jdbc:mysql://replica:3306/main_db\",\n" +
        "          \"poolSize\": 10\n" +
        "        }\n" +
        "      },\n" +
        "      \"externalServices\": [\n" +
        "        {\n" +
        "          \"name\": \"paymentGateway\",\n" +
        "          \"endpoint\": \"https://api.payment.com/v2\",\n" +
        "          \"authentication\": {\n" +
        "            \"method\": \"oauth2\",\n" +
        "            \"credentials\": {\n" +
        "              \"clientId\": \"encrypted-value-here\",\n" +
        "              \"scope\": [\"payments:read\", \"payments:write\"]\n" +
        "            }\n" +
        "          }\n" +
        "        }\n" +
        "      ]\n" +
        "    },\n" +
        "    \"processing\": {\n" +
        "      \"batchSize\": 1000,\n" +
        "      \"threads\": 8,\n" +
        "      \"retryPolicy\": {\n" +
        "        \"maxAttempts\": 3,\n" +
        "        \"backoff\": {\n" +
        "          \"initialInterval\": 1000,\n" +
        "          \"multiplier\": 2.0,\n" +
        "          \"maxInterval\": 10000\n" +
        "        }\n" +
        "      }\n" +
        "    }\n" +
        "  }\n" +
        "}";

    public static final String json4 = "{\n" +
        "  \"content\": {\n" +
        "    \"pages\": [\n" +
        "      {\n" +
        "        \"id\": \"homepage\",\n" +
        "        \"route\": \"/\",\n" +
        "        \"localizedContent\": {\n" +
        "          \"en-US\": {\n" +
        "            \"title\": \"Welcome to Our Platform\",\n" +
        "            \"metaDescription\": \"Discover amazing products and services\",\n" +
        "            \"sections\": [\n" +
        "              {\n" +
        "                \"type\": \"hero\",\n" +
        "                \"heading\": \"Innovation at Your Fingertips\",\n" +
        "                \"subheading\": \"Join thousands of satisfied customers\"\n" +
        "              }\n" +
        "            ]\n" +
        "          },\n" +
        "          \"zh-CN\": {\n" +
        "            \"title\": \"欢迎来到我们的平台\",\n" +
        "            \"metaDescription\": \"发现卓越的产品和服务\",\n" +
        "            \"sections\": [\n" +
        "              {\n" +
        "                \"type\": \"hero\",\n" +
        "                \"heading\": \"触手可及的创新\",\n" +
        "                \"subheading\": \"加入数千名满意的客户\"\n" +
        "              }\n" +
        "            ]\n" +
        "          }\n" +
        "        },\n" +
        "        \"layout\": {\n" +
        "          \"template\": \"modern\",\n" +
        "          \"components\": [\n" +
        "            {\n" +
        "              \"type\": \"navigation\",\n" +
        "              \"position\": \"top\",\n" +
        "              \"items\": [\n" +
        "                {\"label\": \"Home\", \"url\": \"/\"},\n" +
        "                {\"label\": \"Products\", \"url\": \"/products\"}\n" +
        "              ]\n" +
        "            }\n" +
        "          ]\n" +
        "        }\n" +
        "      }\n" +
        "    ],\n" +
        "    \"media\": {\n" +
        "      \"images\": [\n" +
        "        {\n" +
        "          \"id\": \"img-001\",\n" +
        "          \"urls\": {\n" +
        "            \"original\": \"/media/original/photo.jpg\",\n" +
        "            \"thumbnail\": \"/media/thumb/photo.jpg\",\n" +
        "            \"optimized\": \"/media/web/photo.webp\"\n" +
        "          },\n" +
        "          \"altText\": {\n" +
        "            \"en-US\": \"A person using our product\",\n" +
        "            \"zh-CN\": \"使用我们产品的人\"\n" +
        "          }\n" +
        "        }\n" +
        "      ]\n" +
        "    }\n" +
        "  }\n" +
        "}";

    public static final String json5 = "{\n" +
        "  \"home\": {\n" +
        "    \"id\": \"smart-home-001\",\n" +
        "    \"name\": \"未来智能住宅\",\n" +
        "    \"location\": {\n" +
        "      \"coordinates\": {\n" +
        "        \"latitude\": 39.9042,\n" +
        "        \"longitude\": 116.4074\n" +
        "      },\n" +
        "      \"timezone\": \"Asia/Shanghai\",\n" +
        "      \"address\": \"北京市海淀区中关村南大街5号\"\n" +
        "    },\n" +
        "    \"system\": {\n" +
        "      \"status\": \"armed\",\n" +
        "      \"mode\": \"away\",\n" +
        "      \"scenes\": {\n" +
        "        \"morning\": {\n" +
        "          \"enabled\": true,\n" +
        "          \"schedule\": \"06:30\",\n" +
        "          \"actions\": [\n" +
        "            {\n" +
        "              \"device\": \"living-room-blinds\",\n" +
        "              \"action\": \"open\",\n" +
        "              \"level\": 75\n" +
        "            },\n" +
        "            {\n" +
        "              \"device\": \"master-bedroom-lights\",\n" +
        "              \"action\": \"turnOn\",\n" +
        "              \"brightness\": 30,\n" +
        "              \"color\": \"warm-white\"\n" +
        "            },\n" +
        "            {\n" +
        "              \"device\": \"kitchen-coffee-maker\",\n" +
        "              \"action\": \"start\",\n" +
        "              \"settings\": {\n" +
        "                \"strength\": \"medium\",\n" +
        "                \"temperature\": 92,\n" +
        "                \"volume\": 350\n" +
        "              }\n" +
        "            }\n" +
        "          ]\n" +
        "        }\n" +
        "      }\n" +
        "    },\n" +
        "    \"rooms\": [\n" +
        "      {\n" +
        "        \"id\": \"living-room\",\n" +
        "        \"name\": \"客厅\",\n" +
        "        \"area\": 35.5,\n" +
        "        \"devices\": [\n" +
        "          {\n" +
        "            \"id\": \"living-room-tv\",\n" +
        "            \"type\": \"smart-tv\",\n" +
        "            \"brand\": \"Samsung\",\n" +
        "            \"model\": \"QN90A\",\n" +
        "            \"status\": {\n" +
        "              \"power\": \"off\",\n" +
        "              \"source\": \"hdmi1\",\n" +
        "              \"volume\": 45,\n" +
        "              \"currentApp\": \"Netflix\"\n" +
        "            },\n" +
        "            \"capabilities\": [\"4k\", \"hdr\", \"voice-control\", \"airplay\"],\n" +
        "            \"automation\": {\n" +
        "              \"voiceCommands\": [\n" +
        "                {\"command\": \"打开电视\", \"action\": \"powerOn\"},\n" +
        "                {\"command\": \"切换到Netflix\", \"action\": \"launchApp\", \"app\": \"Netflix\"}\n" +
        "              ]\n" +
        "            }\n" +
        "          },\n" +
        "          {\n" +
        "            \"id\": \"living-room-ac\",\n" +
        "            \"type\": \"air-conditioner\",\n" +
        "            \"status\": {\n" +
        "              \"power\": \"on\",\n" +
        "              \"mode\": \"cool\",\n" +
        "              \"temperature\": 24,\n" +
        "              \"fanSpeed\": \"auto\",\n" +
        "              \"humidity\": 45\n" +
        "            },\n" +
        "            \"sensors\": {\n" +
        "              \"temperature\": 23.8,\n" +
        "              \"humidity\": 46,\n" +
        "              \"airQuality\": {\n" +
        "                \"pm2.5\": 12,\n" +
        "                \"co2\": 650,\n" +
        "                \"voc\": 0.8\n" +
        "              }\n" +
        "            },\n" +
        "            \"schedule\": [\n" +
        "              {\n" +
        "                \"days\": [\"weekday\"],\n" +
        "                \"time\": \"08:00\",\n" +
        "                \"action\": \"setTemperature\",\n" +
        "                \"value\": 26\n" +
        "              }\n" +
        "            ]\n" +
        "          }\n" +
        "        ],\n" +
        "        \"climate\": {\n" +
        "          \"temperature\": 23.5,\n" +
        "          \"humidity\": 45,\n" +
        "          \"comfortScore\": 92\n" +
        "        }\n" +
        "      },\n" +
        "      {\n" +
        "        \"id\": \"kitchen\",\n" +
        "        \"name\": \"厨房\",\n" +
        "        \"devices\": [\n" +
        "          {\n" +
        "            \"id\": \"smart-refrigerator\",\n" +
        "            \"type\": \"refrigerator\",\n" +
        "            \"inventory\": [\n" +
        "              {\n" +
        "                \"item\": \"牛奶\",\n" +
        "                \"quantity\": 2,\n" +
        "                \"unit\": \"升\",\n" +
        "                \"expiryDate\": \"2023-12-28\",\n" +
        "                \"compartment\": \"dairy\"\n" +
        "              }\n" +
        "            ],\n" +
        "            \"settings\": {\n" +
        "              \"freezerTemp\": -18,\n" +
        "              \"fridgeTemp\": 4,\n" +
        "              \"ecoMode\": true\n" +
        "            }\n" +
        "          },\n" +
        "          {\n" +
        "            \"id\": \"smart-oven\",\n" +
        "            \"type\": \"oven\",\n" +
        "            \"status\": {\n" +
        "              \"power\": \"off\",\n" +
        "              \"mode\": null,\n" +
        "              \"temperature\": null,\n" +
        "              \"timer\": null\n" +
        "            },\n" +
        "            \"recipes\": [\n" +
        "              {\n" +
        "                \"name\": \"烤鸡\",\n" +
        "                \"preheatTemp\": 200,\n" +
        "                \"cookTemp\": 180,\n" +
        "                \"cookTime\": 60,\n" +
        "                \"steps\": [\n" +
        "                  {\"action\": \"preheat\", \"temperature\": 200, \"duration\": 10},\n" +
        "                  {\"action\": \"cook\", \"temperature\": 180, \"duration\": 50}\n" +
        "                ]\n" +
        "              }\n" +
        "            ]\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ],\n" +
        "    \"security\": {\n" +
        "      \"alarm\": {\n" +
        "        \"status\": \"disarmed\",\n" +
        "        \"sensors\": [\n" +
        "          {\n" +
        "            \"id\": \"front-door-sensor\",\n" +
        "            \"type\": \"door\",\n" +
        "            \"status\": \"closed\",\n" +
        "            \"lastTriggered\": null,\n" +
        "            \"battery\": 85\n" +
        "          },\n" +
        "          {\n" +
        "            \"id\": \"living-room-motion\",\n" +
        "            \"type\": \"motion\",\n" +
        "            \"status\": \"inactive\",\n" +
        "            \"sensitivity\": \"medium\",\n" +
        "            \"battery\": 92\n" +
        "          }\n" +
        "        ],\n" +
        "        \"cameras\": [\n" +
        "          {\n" +
        "            \"id\": \"front-door-camera\",\n" +
        "            \"status\": \"recording\",\n" +
        "            \"resolution\": \"1080p\",\n" +
        "            \"nightVision\": true,\n" +
        "            \"motionDetection\": {\n" +
        "              \"enabled\": true,\n" +
        "              \"sensitivity\": 80,\n" +
        "              \"zones\": [\"front-yard\", \"driveway\"]\n" +
        "            },\n" +
        "            \"storage\": {\n" +
        "              \"type\": \"cloud\",\n" +
        "              \"retentionDays\": 30,\n" +
        "              \"usage\": \"45GB/100GB\"\n" +
        "            }\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      \"access\": {\n" +
        "        \"users\": [\n" +
        "          {\n" +
        "            \"name\": \"张三\",\n" +
        "            \"role\": \"owner\",\n" +
        "            \"permissions\": [\"all\"],\n" +
        "            \"accessMethods\": [\n" +
        "              {\"type\": \"fingerprint\", \"id\": \"finger-001\"},\n" +
        "              {\"type\": \"pin\", \"codeHash\": \"hashed_pin_here\"}\n" +
        "            ]\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    },\n" +
        "    \"energy\": {\n" +
        "      \"consumption\": {\n" +
        "        \"current\": 2.45,\n" +
        "        \"unit\": \"kW\",\n" +
        "        \"today\": 15.3,\n" +
        "        \"monthToDate\": 320.5,\n" +
        "        \"costEstimate\": 285.60\n" +
        "      },\n" +
        "      \"solar\": {\n" +
        "        \"production\": {\n" +
        "          \"current\": 1.8,\n" +
        "          \"today\": 12.5,\n" +
        "          \"total\": 1250.3\n" +
        "        },\n" +
        "        \"battery\": {\n" +
        "          \"level\": 85,\n" +
        "          \"capacity\": \"10kWh\",\n" +
        "          \"status\": \"charging\"\n" +
        "        }\n" +
        "      },\n" +
        "      \"devicesBreakdown\": [\n" +
        "        {\"device\": \"air-conditioner\", \"consumption\": 850, \"percentage\": 35},\n" +
        "        {\"device\": \"refrigerator\", \"consumption\": 150, \"percentage\": 6}\n" +
        "      ]\n" +
        "    },\n" +
        "    \"automation\": {\n" +
        "      \"triggers\": [\n" +
        "        {\n" +
        "          \"name\": \"离家模式\",\n" +
        "          \"conditions\": [\n" +
        "            {\"type\": \"presence\", \"value\": \"allAway\"},\n" +
        "            {\"type\": \"time\", \"value\": \"after 08:30\"}\n" +
        "          ],\n" +
        "          \"actions\": [\n" +
        "            {\"device\": \"all-lights\", \"action\": \"turnOff\"},\n" +
        "            {\"device\": \"thermostat\", \"action\": \"setMode\", \"value\": \"eco\"},\n" +
        "            {\"device\": \"security\", \"action\": \"arm\"}\n" +
        "          ]\n" +
        "        }\n" +
        "      ],\n" +
        "      \"history\": [\n" +
        "        {\n" +
        "          \"timestamp\": \"2023-12-20T18:30:15Z\",\n" +
        "          \"event\": \"motion-detected\",\n" +
        "          \"device\": \"living-room-motion\",\n" +
        "          \"action\": \"triggered-lights\",\n" +
        "          \"result\": \"success\"\n" +
        "        }\n" +
        "      ]\n" +
        "    },\n" +
        "    \"integration\": {\n" +
        "      \"services\": [\n" +
        "        {\n" +
        "          \"name\": \"Google Assistant\",\n" +
        "          \"connected\": true,\n" +
        "          \"lastSync\": \"2023-12-20T10:15:30Z\"\n" +
        "        },\n" +
        "        {\n" +
        "          \"name\": \"Apple HomeKit\",\n" +
        "          \"connected\": true,\n" +
        "          \"scenesSynced\": [\"morning\", \"evening\", \"movie-night\"]\n" +
        "        }\n" +
        "      ],\n" +
        "      \"webhooks\": [\n" +
        "        {\n" +
        "          \"url\": \"https://api.weather.com/alert\",\n" +
        "          \"events\": [\"severe-weather\"],\n" +
        "          \"active\": true\n" +
        "        }\n" +
        "      ]\n" +
        "    },\n" +
        "    \"maintenance\": {\n" +
        "      \"deviceHealth\": [\n" +
        "        {\n" +
        "          \"device\": \"living-room-ac-filter\",\n" +
        "          \"status\": \"needs_replacement\",\n" +
        "          \"lastChanged\": \"2023-09-15\",\n" +
        "          \"nextDue\": \"2023-12-25\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"firmware\": {\n" +
        "        \"updateAvailable\": true,\n" +
        "        \"currentVersion\": \"2.1.3\",\n" +
        "        \"availableVersion\": \"2.2.0\",\n" +
        "        \"securityPatches\": [\"CVE-2023-1234\", \"CVE-2023-5678\"]\n" +
        "      }\n" +
        "    }\n" +
        "  }\n" +
        "}";

    // 包含各种转义字符的复杂结构
    public static final String json6 = "{\n" +
        "  \"user\": {\n" +
        "    \"name\": \"John\\\"Doe\",\n" +
        "    \"description\": \"这是一个\\n多行\\r字符串\\t带有制表符\\\\反斜杠\",\n" +
        "    \"unicode\": \"\\u4E2D\\u6587\\u5B57\\u7B26 \\u2764 \\uD83D\\uDE00\",\n" +
        "    \"special_chars\": \"退格\\b符\\f换页\\r回车\\t制表\\\\反斜杠\",\n" +
        "    \"escape_sequence\": \"\\\"双引号\\\" '单引号' /斜杠\",\n" +
        "    \"control_chars\": \"垂直制表符\\u000B 换页符\\u000C\",\n" +  // 没搞明白jackson对一些特殊控制字符处理的逻辑，先注释掉
        "    \"path\": \"C:\\\\Users\\\\test\\\\file.json\",\n" +
        "    \"regex\": \"^[A-Z]\\\\w+\\\\.txt$\"\n" +
        "  },\n" +
        "  \"list\": [\n" +
        "    \"item1\\u000Aitem1第二行\",\n" +
        "    \"item2\\t\\t双制表符\",\n" +
        "    \"item3\\\\backslash\"\n" +
        "  ],\n" +
        "  \"nested\": {\n" +
        "    \"level1\": {\n" +
        "      \"level2\": {\n" +
        "        \"message\": \"深度\\n嵌套\\r对象\\t测试\"\n" +
        "      }\n" +
        "    }\n" +
        "  },\n" +
        "  \"mixed_unicode\": \"混合: \\u0041\\u0042\\u0043 ASCII \\u03B1\\u03B2\\u03B3 希腊字母\",\n" +
        "  \"isValid\": true,\n" +
        "  \"value\": 3.14159\n" +
        "}";

    public static final String json7 = "{\n" +
        "  \"metadata\": {\n" +
        "    \"timestamp\": \"2024-01-15T12:30:45Z\",\n" +
        "    \"source\": \"API\\u0020Gateway\",\n" +
        "    \"charset\": \"UTF\\u002D8\",\n" +
        "    \"notes\": \"特殊字符测试: \\b退格 \\f换页 \\r\\n换行回车 \\t制表 \\\\反斜线 \\\"引号\"\n" +
        "  },\n" +
        "  \"data\": {\n" +
        "    \"employees\": [\n" +
        "      {\n" +
        "        \"id\": 1,\n" +
        "        \"name\": \"Alice\\u2605\",\n" +
        "        \"department\": \"R\\u0026D\",\n" +
        "        \"email\": \"alice@test\\\\.com\",\n" +
        "        \"bio\": \"热爱编程\\uD83D\\uDCBB\\n擅长Java\\uD83C\\uDF10\",\n" +
        "        \"skills\": [\"Java\\\\Spring\", \"Python/Django\", \"JavaScript\"]\n" +
        "      },\n" +
        "      {\n" +
        "        \"id\": 2,\n" +
        "        \"name\": \"Bob\\u2600\",\n" +
        "        \"department\": \"QA\\\\Test\",\n" +
        "        \"email\": \"bob@test\\\\.com\",\n" +
        "        \"bio\": \"测试专家\\u2699\\uFE0F\\r\\n关注质量\\u2705\",\n" +
        "        \"skills\": [\"Selenium\", \"JUnit/TestNG\", \"API\\u0020Testing\"]\n" +
        "      }\n" +
        "    ],\n" +
        "    \"projects\": [\n" +
        "      {\n" +
        "        \"name\": \"项目\\\"A\\\"\",\n" +
        "        \"status\": \"完成\\u2713\",\n" +
        "        \"path\": \"D:\\\\projects\\\\projectA\\\\config.json\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"项目\\u003CB\\u003E\",\n" +
        "        \"status\": \"进行中\\u23F3\",\n" +
        "        \"path\": \"E:\\\\work\\\\projectB\\\\.gitignore\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  \"escape_examples\": {\n" +
        "    \"unicode_points\": [\n" +
        "      \"\\u0041\", \"\\u0042\", \"\\u0043\", \"\\u03B1\", \"\\u03B2\", \"\\u03B3\"\n" +
        "    ],\n" +
        "    \"control_chars\": {\n" +
        "      \"null_char\": \"\\u0000\",\n" +
        "      \"bell\": \"\\u0007\",\n" +
        "      \"escape\": \"\\u001B\",\n" +
        "      \"delete\": \"\\u007F\"\n" +
        "    },\n" +
        "    \"quotes_and_slashes\": \"双引号: \\\" 单引号: ' 反斜杠: \\\\ 正斜杠: /\"\n" +
        "  },\n" +
        "  \"complex_string\": \"混合转义: \\\"引用\\\"内部有\\n换行和\\t制表，路径: C:\\\\temp\\\\file\\\\n\\\\t特殊.txt\",\n" +
        "  \"special_unicode\": {\n" +
        "    \"emoji\": \"\\uD83D\\uDE00\\uD83D\\uDE03\\uD83D\\uDE04\",\n" +
        "    \"symbols\": \"\\u00A9\\u00AE\\u2122\",\n" +
        "    \"arrows\": \"\\u2190\\u2191\\u2192\\u2193\"\n" +
        "  },\n" +
        "  \"empty_or_null\": {\n" +
        "    \"empty_string\": \"\",\n" +
        "    \"null_value\": null,\n" +
        "    \"whitespace\": \"\\t\\n\\r \\u0020\\u00A0\"\n" +
        "  }\n" +
        "}";

    public static final String baseJson1 = "[]";
    public static final String baseJson2 = "{}";
    public static final String baseJson3 = "[[[]]]";
    public static final String baseJson4 = "123";
    public static final String baseJson5 = "\"abc123\"";


    /**
     * jackson对\\u转义符号有一些特殊的逻辑，有的转，有的不转，暂时不支持整体的结果比对
     * */
    public static final String[] testNormalJsonArr = new String[] {
        json1,json2,json3,json4,json5,
    };

    public static final String[] testJsonArr = new String[] {
        json1,json2,json3,json4,json5,json6,json7,
    };

    public static final String[] testBaseJsonArr = new String[]{
        baseJson1,baseJson2,baseJson3,baseJson4,baseJson5
    };
}
