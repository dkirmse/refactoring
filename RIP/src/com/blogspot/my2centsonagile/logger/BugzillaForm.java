package com.blogspot.my2centsonagile.logger;

import java.util.UUID;

class BugzillaForm
{
    final String title = "Create Ticket";

    protected String id;
    protected String product;
    protected String component;
    protected String priority;
    protected String summary;
    protected String symptom;
    protected String description;

    public BugzillaForm(String product, String component, String priority, String summary, String symptom, String description)
    {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.component = component;
        this.priority = priority;
        this.summary = summary;
        this.symptom = symptom;
        this.description = description;
    }

    public String getId ()
    {
        return id;
    }

    public String getURL ()
    {
        return "https://some.bugzilla.server/bugzilla/enter_bug.cgi?product=${this.product}";
    }
}
