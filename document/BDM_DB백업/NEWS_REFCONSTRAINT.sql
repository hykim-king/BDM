--------------------------------------------------------
--  Ref Constraints for Table NEWS
--------------------------------------------------------

  ALTER TABLE "BDM"."NEWS" ADD CONSTRAINT "FK_USERS_TO_NEWS" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
