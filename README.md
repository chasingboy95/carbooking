# Design document of simple rental car booking API service for customers
**@author 黄云明**

# INDEX
Part1. Requirements analysis	2
System Overview	2
Instructions	2
Tasks	2
Requirements	2
Flow chart	3
Part2. System design	4
Basic Design	4
Functional Analysis	4
Detailed Design	5
Database table	5
API Design	6
Part3. Test Case	9
Available accounts	9
Part4. Appendix	10

---

 
# Part1. Requirements analysis
## System Overview
### Instructions
The car rental system provides users with vehicle rental services, and users can quickly reserve vehicles and return vehicles through the platform.
Tasks
1.	Car management services: including car entry, management, query
2.	Car booking service: including car reservations
Requirements
1.	Car management
  1. Query car list
2.	Car booking order
  1. Add new booking order
  2. Finish booking order
  3. Get bill of booking order
3.	User management
  1. Login 
  2. Logout
**Flow chart**
![image](https://user-images.githubusercontent.com/20021475/174342676-e2f3160c-9879-4df8-9387-66f1da1af1e1.png)
 
 ---
 
# Part2. System design
## Basic Design
Database: mysql5.7
Language: java、vue.js
Proxy: nginx
Deployment: docker
Others: springboot、mybatis、pagehelper、shiro、.etc
## Functional Analysis
1.	**Car management**
The car needs to contain id, name, brand, license plate number, daily price, entry time, current status (available or under maintenance)，and the brand should be separate from the car, contain id and name;
Car management contains functions such as adding new cars (only api now), updating cars (only api now), and querying car lists;
2.	**Car booking order**
Add new booking order
  1. To add a car booking order, you need to provide order number, car id, user id, expected start date, expected end date, actual end date, and current order status.
  2. When add a car booking order, show check if the vehicle is available on the expected date range.
Finish booking order
	1. Check if the expected end date is less than the current date, if so, take the current date, otherwise take the expected date as the actual end date 
Get bill of booking order
	1.  Calculate car rental days multiplied by unit price
3.	**User management**
User management need user info which contains id, name, phone, password, role, status; and token info to maintains login status, contains token, expire time, update time;
Login 
	1. Login with user id and password, and check legality, after login success redirect to the orders page
Logout
	1.Logout 
## Detailed Design
### Database table
![image](https://user-images.githubusercontent.com/20021475/174343766-e7591653-9dac-4fcf-922c-af7aa1459173.png)
![image](https://user-images.githubusercontent.com/20021475/174343809-3262169b-057b-45c1-9b1c-e5444c9b1a04.png)
![image](https://user-images.githubusercontent.com/20021475/174343839-923f465d-255a-4cea-ad2b-4e5c16fc7d34.png)
![image](https://user-images.githubusercontent.com/20021475/174343845-9c9c5030-ff1b-4fb8-9480-4591371b01fa.png)
![image](https://user-images.githubusercontent.com/20021475/174343859-ee195210-7bdc-4e3b-bd33-cbe20267915f.png)

 ``` mysql
 CREATE TABLE `car` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '车名',
  `brand_id` int(10) NOT NULL COMMENT '品牌id',
  `price` decimal(10,2) NOT NULL COMMENT '单日价格',
  `plate_number` varchar(255) NOT NULL COMMENT '车牌',
  `created_at` timestamp NULL DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_brand_id` (`brand_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
 
 CREATE TABLE `car_booking_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `car_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `start_time` date DEFAULT NULL,
  `estimated_end_time` date DEFAULT NULL,
  `actual_end_time` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_car_id` (`car_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='car booking order';

CREATE TABLE `car_brand` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
 	CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色（0-普通用户 1-管理员 2-超级管理）',
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

```

## API Design
Available in swagger: http://113.31.117.118:8848/car-booking/swagger-ui.html
 
---

# Part3. Test Case
**Available accounts**
ID	PASSWORD
10001	123456
10002	123456
10003	123456
10004	123456
10005	123456
10006	123456
10007	123456
10008	123456
10009	123456
10010	123456

 
# Part4. Appendix
System web address : http://carbooking.riding4.fun  or https:// carbooking.riding4.fun
Swgger api address: http://113.31.117.118:8848/car-booking/swagger-ui.html

Time is limited, there are deficiencies in design and implementation, please correct me.
