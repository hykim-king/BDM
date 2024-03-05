--------------------------------------------------------
--  Constraints for Table BULLETIN
--------------------------------------------------------

  ALTER TABLE "BDM"."BULLETIN" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "BDM"."BULLETIN" MODIFY ("CONTENTS" NOT NULL ENABLE);
  ALTER TABLE "BDM"."BULLETIN" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."BULLETIN" ADD CONSTRAINT "PK_BULLETIN" PRIMARY KEY ("POST_NO")
  USING INDEX "BDM"."PK_BULLETIN"  ENABLE;
