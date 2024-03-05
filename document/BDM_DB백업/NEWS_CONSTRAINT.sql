--------------------------------------------------------
--  Constraints for Table NEWS
--------------------------------------------------------

  ALTER TABLE "BDM"."NEWS" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NEWS" MODIFY ("CONTENTS" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NEWS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."NEWS" ADD CONSTRAINT "PK_NEWS" PRIMARY KEY ("POST_NO")
  USING INDEX "BDM"."PK_NEWS"  ENABLE;
