--------------------------------------------------------
--  Constraints for Table IMAGE
--------------------------------------------------------

  ALTER TABLE "BDM"."IMAGE" MODIFY ("UUID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."IMAGE" ADD CONSTRAINT "PK_IMAGE" PRIMARY KEY ("UUID", "SEQ")
  USING INDEX "BDM"."PK_IMAGE"  ENABLE;
