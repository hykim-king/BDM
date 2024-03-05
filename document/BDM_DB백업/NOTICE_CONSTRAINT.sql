--------------------------------------------------------
--  Constraints for Table NOTICE
--------------------------------------------------------

  ALTER TABLE "BDM"."NOTICE" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NOTICE" MODIFY ("CONTENTS" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NOTICE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NOTICE" ADD CONSTRAINT "PK_NOTICE" PRIMARY KEY ("POST_NO")
  USING INDEX "BDM"."PK_NOTICE"  ENABLE;
