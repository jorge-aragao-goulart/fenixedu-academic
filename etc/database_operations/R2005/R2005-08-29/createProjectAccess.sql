CREATE TABLE PROJECT_ACCESS(
  ID_INTERNAL int(11) NOT NULL auto_increment,
  KEY_PERSON int(11) NOT NULL,
  KEY_PROJECT_COORDINATOR int(11) NOT NULL ,
  KEY_PROJECT int(11) NOT NULL,
  BEGIN_DATE TIMESTAMP NOT NULL,
  END_DATE TIMESTAMP NOT NULL,
  ACK_OPT_LOCK int(11) default '0',
  PRIMARY KEY  (ID_INTERNAL),
  UNIQUE KEY U1(key_PERSON,KEY_PROJECT)
) TYPE=InnoDB;