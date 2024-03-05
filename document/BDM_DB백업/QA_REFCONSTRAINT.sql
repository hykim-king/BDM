--------------------------------------------------------
--  Ref Constraints for Table QA
--------------------------------------------------------

  ALTER TABLE "BDM"."QA" ADD CONSTRAINT "FK_USERS_TO_QA" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
