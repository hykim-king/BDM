--------------------------------------------------------
--  Constraints for Table EAT
--------------------------------------------------------

  ALTER TABLE "BDM"."EAT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "BDM"."EAT" MODIFY ("CODE" NOT NULL ENABLE);
  ALTER TABLE "BDM"."EAT" MODIFY ("DIVS" NOT NULL ENABLE);
  ALTER TABLE "BDM"."EAT" MODIFY ("AMOUNT" NOT NULL ENABLE);
  ALTER TABLE "BDM"."EAT" ADD CONSTRAINT "PK_EAT" PRIMARY KEY ("ID", "REG_DT", "CODE")
  USING INDEX "BDM"."PK_EAT"  ENABLE;
