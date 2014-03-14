alter table ACCOUNT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table ACCOUNT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;

alter table ACCOUNT DROP INDEX KEY_PARTY_2;
alter table ACCOUNT DROP INDEX KEY_PARTY_3;

alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_EVENT_2;
alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_EVENT_3;

alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_RESPONSIBLE_USER_2;
alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_RESPONSIBLE_USER_3;

alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table ACCOUNTING_TRANSACTION DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;

alter table ACCOUNTING_ENTRY DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_ACCOUNT_2;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_ACCOUNT_3;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_RECEIPT_2;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_RECEIPT_3;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_ACCOUNTING_TRANSACTION_2;
alter table ACCOUNTING_ENTRY DROP INDEX KEY_ACCOUNTING_TRANSACTION_3;

alter table ACCOUNTING_EVENT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table ACCOUNTING_EVENT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;
alter table ACCOUNTING_EVENT DROP INDEX KEY_PERSON_2;
alter table ACCOUNTING_EVENT DROP INDEX KEY_PERSON_3;
alter table ACCOUNTING_EVENT DROP INDEX KEY_CANDIDACY_3;
alter table ACCOUNTING_EVENT DROP INDEX KEY_CANDIDACY_2;

alter table RECEIPT DROP INDEX KEY_PERSON_2;
alter table RECEIPT DROP INDEX KEY_PERSON_3;
alter table RECEIPT DROP INDEX KEY_CONTRIBUTOR_2;
alter table RECEIPT DROP INDEX KEY_CONTRIBUTOR_3;
alter table RECEIPT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table RECEIPT DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;

alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_ROOT_DOMAIN_OBJECT_2;
alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_ROOT_DOMAIN_OBJECT_3;
alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_RECEIPT_2;
alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_RECEIPT_3;
alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_EMPLOYEE_2;
alter table RECEIPT_PRINT_VERSION DROP INDEX KEY_EMPLOYEE_3;