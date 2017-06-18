package parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.Wether;

/**
 * Created by Lamine Dieng on 17/06/2017.
 */

public class WeatherXmlParser {

    public static List<Wether> parseFeed(String content)
    {
        try {

            boolean inDataItemTag = false;
            String currentTagName = "";
            Wether wether = null;
            List<Wether> wetherList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if(currentTagName.equals("root"))
                        {
                            inDataItemTag = true;
                            wether = new Wether();
                            wetherList.add(wether);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("root"))
                        {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if(inDataItemTag && wether != null)
                        {
                            switch (currentTagName)
                            {
                                case "region":
                                    wether.setLocationRegion(parser.getText());
                                    break;
                                case "temp_c":
                                    wether.setCurrentTempC(parser.getText());
                                    break;
                                case "text":
                                    wether.setConditionText(parser.getText());
                                    break;
                                case "icon":
                                    wether.setConditionIcon(parser.getText());
                                    break;
                                case "wind_mph":
                                    wether.setWindMph(parser.getText());
                                    break;
                                case "last_updated":
                                    wether.setCurrentLastUpdated(parser.getText());
                                    break;
                                case "maxtemp_c":
                                    wether.setMaxTemp(parser.getText());
                                    break;
                                case "mintemp_c":
                                    wether.setMinTemp(parser.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                }

                eventType = parser.next();

            }

            return wetherList;

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }
}
