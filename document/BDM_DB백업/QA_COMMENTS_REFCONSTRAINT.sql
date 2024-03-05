--------------------------------------------------------
--  Ref Constraints for Table QA_COMMENTS
--------------------------------------------------------

  ALTER TABLE "BDM"."QA_COMMENTS" ADD CONSTRAINT "FK_QA_TO_COMMENTS" FOREIGN KEY ("POST_NO")
	  REFERENCES "BDM"."QA" ("POST_NO") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BDM"."QA_COMMENTS" ADD CONSTRAINT "FK_USERS_TO_QA_COMMENTS" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
