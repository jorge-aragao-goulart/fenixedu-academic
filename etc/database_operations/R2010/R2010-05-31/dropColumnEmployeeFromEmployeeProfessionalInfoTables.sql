alter table `EMPLOYEE_CONTRACT_SITUATION` drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_FUNCTIONS_ACCUMULATION`  drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_PROFESSIONAL_CATEGORY`  drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_PROFESSIONAL_CONTRACT`  drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_PROFESSIONAL_EXEMPTION`  drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_PROFESSIONAL_REGIME`  drop column `OID_EMPLOYEE`;
alter table `EMPLOYEE_PROFESSIONAL_RELATION`  drop column `OID_EMPLOYEE`;


delete from EMPLOYEE_CONTRACT_SITUATION;
delete from EMPLOYEE_FUNCTIONS_ACCUMULATION;
delete from EMPLOYEE_PROFESSIONAL_CATEGORY;
delete from EMPLOYEE_PROFESSIONAL_CONTRACT;
delete from EMPLOYEE_PROFESSIONAL_EXEMPTION;
delete from EMPLOYEE_PROFESSIONAL_REGIME;
delete from EMPLOYEE_PROFESSIONAL_RELATION;