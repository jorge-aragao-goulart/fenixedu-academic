insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day), 'UB_MANAGER','UB_EXECUTION_COURSE_RESPONSIBLE', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Responsáveis de Disciplinas',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_MASTER_DEGREE_STUDENT', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Estudantes de Mestrado',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_DEGREE_STUDENT', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Estudantes de Licenciatura',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_DEGREE_COORDINATOR', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Coordenadores de Licenciatura',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_MASTER_DEGREE_COORDINATOR', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Coordenadores de Mestrado',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_EMPLOYEE', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Funcionários',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_MANAGER','UB_TEACHER', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Docentes',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_WEBSITE_MANAGER', 'UB_PUBLIC', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Eventos',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;
insert into ANNOUNCEMENT_BOARD (MANDATORY,CREATION_DATE,UNIT_PERMITTED_WRITE_GROUP_TYPE, UNIT_PERMITTED_READ_GROUP_TYPE, UNIT_PERMITTED_MANAGEMENT_GROUP_TYPE, KEY_ROOT_DOMAIN_OBJECT, KEY_PARTY, NAME, OJB_CONCRETE_CLASS) select 1,date_sub(now(),interval 8 day),'UB_WEBSITE_MANAGER', 'UB_PUBLIC', 'UB_MANAGER', 1, PARTY.ID_INTERNAL,
'Notícias',
'net.sourceforge.fenixedu.domain.messaging.UnitAnnouncementBoard' from PARTY left join ACCOUNTABILITY on ACCOUNTABILITY.KEY_CHILD_PARTY = PARTY.ID_INTERNAL where PARTY.OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.organizationalStructure.Unit' and ACCOUNTABILITY.ID_INTERNAL is null and PARTY.ID_INTERNAL is not null and PARTY.ACRONYM is not null;


insert into ANNOUNCEMENT 
(
KEY_ROOT_DOMAIN_OBJECT,
KEY_ANNOUNCEMENT_BOARD,
CREATION_DATE,
SUBJECT,
BODY,
VISIBLE,
AUTHOR,
AUTHOR_EMAIL,
EXCERPT,
PUBLICATION_BEGIN,
PUBLICATION_END,
REFERED_SUBJECT_BEGIN,
REFERED_SUBJECT_END,
KEYWORDS
) 
select 
1,
ANNOUNCEMENT_BOARD.ID_INTERNAL, 
WEBSITE_ITEM.CREATION_DATE_DATE_TIME,
concat("pt",length(WEBSITE_ITEM.TITLE),":",WEBSITE_ITEM.TITLE), 
concat("pt",length(WEBSITE_ITEM.MAIN_ENTRY_TEXT),":",WEBSITE_ITEM.MAIN_ENTRY_TEXT),
WEBSITE_ITEM.PUBLISHED,
WEBSITE_ITEM.AUTHOR_NAME,
WEBSITE_ITEM.AUTHOR_EMAIL,
concat("pt",length(WEBSITE_ITEM.EXCERPT),":",WEBSITE_ITEM.EXCERPT),
WEBSITE_ITEM.ONLINE_BEGIN_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ONLINE_END_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ITEM_BEGIN_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ITEM_END_DAY_YEAR_MONTH_DAY,
concat("pt",length(WEBSITE_ITEM.KEYWORDS),":",WEBSITE_ITEM.KEYWORDS)

from WEBSITE_ITEM inner join WEBSITE_SECTION on WEBSITE_ITEM.KEY_WEB_SITE_SECTION=WEBSITE_SECTION.ID_INTERNAL inner join ANNOUNCEMENT_BOARD on ANNOUNCEMENT_BOARD.NAME=WEBSITE_SECTION.NAME where WEBSITE_SECTION.NAME="Eventos";

insert into ANNOUNCEMENT 
(
KEY_ROOT_DOMAIN_OBJECT,
KEY_ANNOUNCEMENT_BOARD,
CREATION_DATE,
SUBJECT,
BODY,
VISIBLE,
AUTHOR,
AUTHOR_EMAIL,
EXCERPT,
PUBLICATION_BEGIN,
PUBLICATION_END,
REFERED_SUBJECT_BEGIN,
REFERED_SUBJECT_END,
KEYWORDS
) 
select 
1, 
ANNOUNCEMENT_BOARD.ID_INTERNAL, 
WEBSITE_ITEM.CREATION_DATE_DATE_TIME,
concat("pt",length(WEBSITE_ITEM.TITLE),":",WEBSITE_ITEM.TITLE), 
concat("pt",length(WEBSITE_ITEM.MAIN_ENTRY_TEXT),":",WEBSITE_ITEM.MAIN_ENTRY_TEXT),
WEBSITE_ITEM.PUBLISHED,
WEBSITE_ITEM.AUTHOR_NAME,
WEBSITE_ITEM.AUTHOR_EMAIL,
concat("pt",length(WEBSITE_ITEM.EXCERPT),":",WEBSITE_ITEM.EXCERPT),
WEBSITE_ITEM.ONLINE_BEGIN_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ONLINE_END_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ITEM_BEGIN_DAY_YEAR_MONTH_DAY,
WEBSITE_ITEM.ITEM_END_DAY_YEAR_MONTH_DAY,
concat("pt",length(WEBSITE_ITEM.KEYWORDS),":",WEBSITE_ITEM.KEYWORDS)

from WEBSITE_ITEM,ANNOUNCEMENT_BOARD,WEBSITE_SECTION where WEBSITE_SECTION.NAME like "Notícias" and ANNOUNCEMENT_BOARD.NAME like "Notícias" and WEBSITE_ITEM.KEY_WEB_SITE_SECTION=WEBSITE_SECTION.ID_INTERNAL;

UPDATE ANNOUNCEMENT set ANNOUNCEMENT.LAST_MODIFICATION=ANNOUNCEMENT.CREATION_DATE where ANNOUNCEMENT.LAST_MODIFICATION is null;