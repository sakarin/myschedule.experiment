import deng.quartz.jboss.examples.*;

import javax.jms.Session;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.quartz.*;
import org.quartz.jobs.ee.jms.JmsHelper;
import org.quartz.jobs.ee.jms.SendDestinationMessageJob;

JobDataMap jobData = new JobDataMap();
jobData.put(JmsHelper.JMS_CONNECTION_FACTORY_JNDI, "java:/ConnectionFactory");
jobData.put(JmsHelper.JMS_DESTINATION_JNDI, "java:/queue/QuartzQueue");
jobData.put(JmsHelper.JMS_USE_TXN, "true");
jobData.put(JmsHelper.JMS_ACK_MODE, Session.AUTO_ACKNOWLEDGE);
jobData.put(JmsHelper.JMS_MSG_FACTORY_CLASS_NAME, SimpleJmsMessageFactory.class.getName());
jobData.put(JmsHelper.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
jobData.put(JmsHelper.PROVIDER_URL, "localhost:1099");
JobDetail job = new JobDetail("jmsJob", "DEFAULT", SendDestinationMessageJob.class);
job.setJobDataMap(jobData);
//Trigger trigger = new SimpleTrigger("jmsJob", "DEFAULT");
//trigger.setRepeatCount(-1)
//trigger.setRepeatInterval(60*1000)

trigger = new CronTrigger('jmsJob', 'DEFAULT', '0 0/5 * * * ?')

quartzScheduler.scheduleJob(job, trigger)