ALTER TABLE FILE ADD COLUMN KEY_PROJECT_SUBMISSION INT(11) NULL;
ALTER TABLE FILE CHANGE COLUMN PERMITTED_GROUP_TYPE FILE_ITEM_PERMITTED_GROUP_TYPE VARCHAR(50) NULL;
ALTER TABLE FILE CHANGE COLUMN DSPACE_BITSTREAM_IDENTIFICATION EXTERNAL_STORAGE_IDENTIFICATION VARCHAR(255) NOT NULL;
ALTER TABLE FILE CHANGE COLUMN SIZE SIZE INT(11) NOT NULL;
ALTER TABLE FILE ADD COLUMN OJB_CONCRETE_CLASS VARCHAR(255) NULL;

UPDATE FILE SET OJB_CONCRETE_CLASS = 'net.sourceforge.fenixedu.domain.FileItem';

ALTER TABLE FILE CHANGE COLUMN OJB_CONCRETE_CLASS OJB_CONCRETE_CLASS VARCHAR(255) NOT NULL;