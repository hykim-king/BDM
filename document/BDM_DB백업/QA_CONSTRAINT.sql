--------------------------------------------------------
--  Constraints for Table QA
--------------------------------------------------------

  ALTER TABLE "BDM"."QA" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "BDM"."QA" MODIFY ("CONTENTS" NOT NULL ENABLE);
  ALTER TABLE "BDM"."QA" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."QA" ADD CONSTRAINT "PK_QA" PRIMARY KEY ("POST_NO")
  USING INDEX "BDM"."PK_QA"  ENABLE;
