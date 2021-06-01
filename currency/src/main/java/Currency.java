/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.rapidoid.u.U;
import org.rapidoid.setup.On;
import org.rapidoid.http.Req;
import com.nosto.config.Config;
import com.nosto.container.Container;
import org.rapidoid.job.Jobs;
import org.rapidoid.setup.App;

import com.nosto.lib.http.Request;
import com.nosto.internal.currency.infrastructure.external.http.ConverterHttp;
import com.nosto.internal.currency.infrastructure.external.ExternalCurrency;
import com.nosto.http.Route;
/**
 *
 * @author angryronald
 */
public class Currency {
    public static void main (String[] args) {
        System.out.println("Currency.main()");
        
        Config config = new Config();
        On.address(config.GetRegisteredHost()).port(config.GetExposedPort());
        
        Container container = new Container(config);
        Route.RegisterAllRoute(container);
//        // define a handler for the route GET /hello
//        On.get("/hello").json(() -> U.map("msg", "Hello, world!"));
//        
//        //On.get("/x").json("x");
        Request client = new Request();
        client.AddParameter("access_key", "d774eeef0d7928e7920a53050c0623f1");
        client.SetBaseURL("http://api.exchangeratesapi.io/v1");
        ConverterHttp converter = new ConverterHttp(client);
        ExternalCurrency source = new ExternalCurrency("EUR",6500.0);
        try{
            ExternalCurrency result = converter.Convert(source, "IDR");
            System.out.println(result);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
