package com.redhat.training.ejb;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.redhat.training.ui.EightBall;


@RunWith(Arquillian.class)
public class EJBTest {
    @Inject
    private EightBall eightBall;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"convert-ejb-test.war").addClass(EightBall.class).addClass(Magic8BallBean.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Test
    public void testEightBallEJB() {
    	 	eightBall.setQuestion("Is it going to rain today?");
        Assert.assertNotNull(eightBall.ask());
    }
}
