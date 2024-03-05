--------------------------------------------------------
--  Constraints for Table HEART
--------------------------------------------------------

  ALTER TABLE "BDM"."HEART" MODIFY ("POST_NO" NOT NULL ENABLE);
  ALTER TABLE "BDM"."HEART" ADD CONSTRAINT "PK_HEART" PRIMARY KEY ("POST_NO", "ID")
  USING INDEX "BDM"."PK_HEART"  ENABLE;
