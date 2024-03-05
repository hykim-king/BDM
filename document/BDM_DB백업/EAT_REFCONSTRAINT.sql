--------------------------------------------------------
--  Ref Constraints for Table EAT
--------------------------------------------------------

  ALTER TABLE "BDM"."EAT" ADD CONSTRAINT "FK_USERS_TO_EAT" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BDM"."EAT" ADD CONSTRAINT "FK_NUTRIENT_TO_EAT" FOREIGN KEY ("CODE")
	  REFERENCES "BDM"."NUTRIENT" ("CODE") ON DELETE CASCADE ENABLE;
