alter table ACCOUNTING_ENTRY add column KEY_CREDIT_NOTE int(11) default NULL;
alter table ACCOUNTING_ENTRY add KEY (KEY_CREDIT_NOTE);
alter table ACCOUNTING_ENTRY add column KEY_ADJUSTMENT_CREDIT_NOTE_ENTRY int(11) default NULL;
alter table ACCOUNTING_ENTRY add KEY (KEY_ADJUSTMENT_CREDIT_NOTE_ENTRY);