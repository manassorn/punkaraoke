package karaoke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;

import com.sun.jna.platform.WindowUtils;


public class SongInfoOverlay extends Window {
	
	String songId = "";
	String author = "";
	String name = "";
	String audioChannel = "Mono Left";
	
	boolean songIdVisible = false;
	boolean authorVisible = false;
	boolean nameVisible = false;
	boolean audioChannelVisible = true;
	
    public SongInfoOverlay(Window owner) {
        super(owner, WindowUtils.getAlphaCompatibleGraphicsConfiguration());
        setBackground(new Color(0, 0, 0, 0));
//        final Window that = this;
//        owner.addComponentListener(new ComponentListener() {
//            public void componentResized(ComponentEvent e) {
//            	Component component = e.getComponent();
//            	that.setSize(component.getWidth(), component.getHeight());
////            	System.out.println("RESIZE");
//            	that.validate();
//            }
//
//			@Override
//			public void componentHidden(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentMoved(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void componentShown(ComponentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//        });
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        if(songIdVisible) {
        	drawString(g2, "รหัสเพลง " + songId, 100);
        }
        if(authorVisible) {
        	drawString(g2, "ผู้แต่ง " + author, 160);
        }
        if(nameVisible) {
        	drawString(g2, "ชื่อเพลง" + name, 210);
        }
        if(audioChannelVisible) {
        	drawString(g2, audioChannel, getHeight() - 50);
        }
        System.out.println("PAINT");
    }
    
    void drawString(Graphics2D g, String str, int y) {
    	int x = 100, paddingX = 10, paddingY = 5;
    	
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(str, g);

        g.setColor(new Color(0x000240));
        g.fillRect(x,
                   y - fm.getAscent(),
                   (int) rect.getWidth() + paddingX * 2,
                   (int) rect.getHeight() + paddingY * 2);
        
        g.setColor(Color.WHITE);
        g.drawString(str, x + paddingX, y + paddingY);
    }
    
    public void showSongId(String songId) {
    	this.songId = songId;
    	for(int i = 0; i < 5 - songId.length(); i++) {
    		this.songId += " _";
    	}
    	songIdVisible = true;
    }
    
    public void hideSongId() {
    	songIdVisible = false;
    }
    
    public void showSongInfo(String author, String name) {
    	this.author = author;
    	this.name = name;
    	authorVisible = true;
    	nameVisible = true;
    }
    
    public void hideSongInfo() {
    	authorVisible = false;
    	nameVisible = false;
    }
    
    public void showAudioChannel(String channel) {
    	this.audioChannel = channel;
    	this.audioChannelVisible = true;
    }
    
    public void hideAudioChannel() {
    	audioChannelVisible = false;
    }
    
    public void reset() {
    	songId = "";
    	author = "";
    	name = "";
    	audioChannel = "";
    }
}
