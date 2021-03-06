package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.animations.AnimatedImage;
import com.mygdx.game.animations.AnimatedImage2;
import com.mygdx.game.animations.BurstParticle;
import com.mygdx.game.animations.EyeParticle;
import com.mygdx.game.animations.FluxParticle;
import com.mygdx.game.animations.KnobParticle;
import com.mygdx.game.champs.Annie;
import com.mygdx.game.champs.Renek;
import com.mygdx.game.champs.TahmK;
import com.mygdx.game.champs.Talon;
import com.mygdx.game.resources.Assets;
import com.mygdx.game.resources.BodyEditorLoader;
import com.mygdx.game.ui.Glist;
import com.mygdx.game.ui.MultiActorGestureListener;
import com.mygdx.game.ui.MultiGestureDetector;
import com.mygdx.game.ui.SimpleDirectionGestureDetector;

import java.util.Iterator;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.mygdx.game.RyzeGame.HEIGHT;
import static com.mygdx.game.RyzeGame.WIDTH;

/**
 * Created by Paradox on 2/18/2017.
 */

public class GameState4 extends State {

    private final String TAG = getClass().getSimpleName();
    private Rectangle cursor;
    private InputMultiplexer multi;
    private MultiGestureDetector list;
    private float alpha;
    private float loaderspeed=5f;
    private float overcount=0f;
    private float bubblecount=0f;
    private float random;
    private float randomReng;
    private float flingtime;
    private float scaleeff = 2f;
    private float[] tint = {0.2f, 0.204f, 1,1, 0.996f, 0,1, 0.2f, 0.2f,0.2f, 1, 0.2f};
    private float[] fluxertint = {1f, 0.831f, 0.047f};
    private float[] fluxertint2 = {1, 0.122f, 0.047f};
    private float[] bursttint = {0, 0.004f, 1,1, 0.2f, 0.2f,0.2f, 1, 0.2f};
    ShapeRenderer debugRenderer = new ShapeRenderer();
    Box2DDebugRenderer debugRenderer2 = new Box2DDebugRenderer();
    World world;

    Talon talon;
    TahmK tahm;
    Talon talonwarp;
    TahmK tahmwarp;
    Renek ren;
    Annie ann;
    //make class
    Image aurelion;
    Image ryze;
    Image rengar;

    private com.badlogic.gdx.scenes.scene2d.ui.Image bg;
    private com.badlogic.gdx.scenes.scene2d.ui.Image bg2;
    private com.badlogic.gdx.scenes.scene2d.ui.Image bg3;
    private com.badlogic.gdx.scenes.scene2d.ui.Image bg4;
    private com.badlogic.gdx.scenes.scene2d.ui.Image overload;
    private com.badlogic.gdx.scenes.scene2d.ui.Image runepris;
    private  com.badlogic.gdx.scenes.scene2d.ui.Image spellflux;
    private com.badlogic.gdx.scenes.scene2d.ui.Image realmwarp;
    //private com.badlogic.gdx.scenes.scene2d.ui.Image realmcircle;
    private Image overloadflux;
    private Image runeprisflux;
    private Image talonwall1, talonwall2;

    com.mygdx.game.animations.AnimatedImage tahmbreak;
    AnimatedImage runetrap;
    AnimatedImage runetrapflux;
    AnimatedImage loader;
    AnimatedImage realmcircle;
    AnimatedImage2 tahmbubble;
    AnimatedImage2 talondags;
    AnimatedImage2 talonult;
    AnimatedImage2 lightbeam;
    AnimatedImage2 aura;
    AnimatedImage2 claw;
    AnimatedImage2 tahmult;
    AnimatedImage2 ashearrow;

    Stage stage;
    Stage stage2;
    Stage stage3;
    Stage stage4;

    private float elapsedTime;
    private float overloadTime;
    private float elapsedTimeRune;
    private float elapsedTimeWarp;
    private Vector3 velocity;
    private Vector2 start;
    private Vector2 end;

    private Vector3 touchPos;
    private float angle;
    private float delta;
    private float runeprisX,runeprisY=0;
    private int[] spot = new int[]{0,1,2,3};
    private int w,h;
    private float counter,tostart=1,tofull=101;
    private float startX, endX;
    private float flingXTalon, flingYTalon, flingXTahm, flingYTahm, flingXRen, flingYRen, flingXAnn, flingYAnn;

    SpriteBatch batch;

    float spd;
    int lvl;

    private Rectangle screenBounds;
    private Rectangle overBounds;
    private Rectangle bubbleBounds;
    private Rectangle loaderBounds;
    private Rectangle prisBounds;
    private Rectangle warpBounds;
    private Rectangle rengoBounds;
    //private Array<Rectangle> arrowBounds;
    private Rectangle arrowBounds;

    private Rectangle q1;
    private Rectangle q2;
    private Rectangle q3;
    private Rectangle q4;
    private Rectangle realmbounds;
    private Rectangle wallBounds,wallBounds2;

    private int crystallize;

    Preferences prefs = Gdx.app.getPreferences("Ryze");
    private long crystals=0;
    private long crystals2=0;
    private long crystals3=0;
    private long crystals4=0;
    private long lastArrowTime;

    private boolean stopCol = false;
    private boolean timersonce = false;
    private boolean arrowstop1,arrowstop2,arrowstop3,arrowstop4=false;
    private boolean fireArrowOnce = false;
    private boolean rengoStop = false;
    private boolean fizzle1, fizzle2=false;
    private boolean swapOnce = false;
    private boolean talonscale,talonscale2 = false;
    private boolean aureltouch1,aureltouch2,aureltouch3,aureltouch4;
    private boolean runeimpactstart = false;
    private boolean stopTrap =false;
    private boolean ryzecome = false;
    private boolean loadermove = false;
    private boolean usetalonult = false;
    private boolean lightuse, useonce = false;
    private boolean fluxpress,prispress,loadpress;
    private boolean jumped = false;
    private boolean tapcancel,tapcancel2,tapcancel3,tapcancel4 = false;
    private boolean tapEvent = false;
    private boolean flinger, flingonce = false;
    private boolean rakeonce = false;
    private boolean hit1,hit2,hit3, hit4 = false;
    private boolean scaleonce1,scaleonce2,scaleonce3 = false;
    private boolean scorestop1,scorestop2,scorestop3,scorestop4,scorestop5,scorestop6,scorestop7,scorestop8 = false;
    private boolean playonce = false;
    private boolean playwice = false;
    private boolean level2 = false;
    private boolean level3 = false;
    private boolean level4 = false;
    private boolean isTouched = false;
    private boolean talonchase = false;
    private boolean blooddrawn = false;
    private boolean frozen = false;
    private boolean bouncefinished,bouncefinished2,bouncefinished3,bouncefinished4 = false;
    private boolean barmax = false;
    private boolean talontouch = false;
    private boolean tahmtouch = false;
    private boolean rentouch = false;
    private boolean anntouch = false;
    private boolean inside1,inside2,inside3,inside4 = false;
    private boolean fluxtap = false;
    private boolean overfluxtap = false;
    private boolean runefluxtap = false;
    private Action scaleupX,scaledownX,scaleupY,scaledownY;
    private Action scaleupXTahm,scaledownXTahm,scaleupYTahm,scaledownYTahm;
    private Action scaleupXRen,scaledownXRen,scaleupYRen,scaledownYRen;
    private Action scaleupXAnn,scaledownXAnn,scaleupYAnn,scaledownYAnn;
    private Action flingforward, flingback;
    private Action runeimpactact;
    private Action flingforwardTahm, flingbackTahm;
    private Action flingforwardRen, flingbackRen;
    private Action flingforwardAnn, flingbackAnn;

    private FluxParticle fluxer;
    private FluxParticle overfluxer;
    private FluxParticle runefluxer;
    //private KnobParticle knob;
    private TextureAtlas fluxatlas;
    //private TextureAtlas knobatlas;
    private TextureAtlas runeatlas = new TextureAtlas();
    private TextureAtlas burstatlas = new TextureAtlas();

    private EyeParticle eye1;
    private EyeParticle eye2;
    private EyeParticle eye3;
    private EyeParticle eye4;
    private EyeParticle eye5;
    private EyeParticle eye6;
    private EyeParticle eye7;
    private EyeParticle eye8;
    private BurstParticle burst;

    private Sound music;
    private Sound music2;
    private Sound music3;
    private Sound music4;
    private Sound music5;

    Skin skin;
    TextureRegionDrawable textureBar;
    TextureRegionDrawable textureBar2;
    ProgressBar bar;
    ProgressBar.ProgressBarStyle barStyle;


    public GameState4(StateManager manager, float speed, int level){
        super(manager);

        spd = speed;
        lvl = level;

        Assets.load();

        music = Gdx.audio.newSound(Gdx.files.internal("music/Tahmexplode.wav"));
        music.setVolume(0,2.0f);
        music2 = Gdx.audio.newSound(Gdx.files.internal("music/Talonhit.wav"));
        music2.setVolume(1,2.0f);
        music3 = Gdx.audio.newSound(Gdx.files.internal("music/Talonspeed.wav"));
        music3.setVolume(2,2.0f);
        music4 = Gdx.audio.newSound(Gdx.files.internal("music/Runecompleted.wav"));
        music4.setVolume(3,2.0f);
        music5 = Gdx.audio.newSound(Gdx.files.internal("music/Warphit.wav"));
        music5.setVolume(4,2.0f);

        crystals = prefs.getLong("crystals");

        //Atlas
        fluxatlas = new TextureAtlas();
        //knobatlas =new TextureAtlas();
        scaleupX = Actions.scaleBy(-0.5f,0.3f,.4f,Interpolation.bounceOut);
        scaledownX = Actions.scaleBy(0.5f,-0.3f,.4f,Interpolation.bounceOut);
        scaleupY=Actions.scaleBy(.3f,-0.5f,.4f,Interpolation.bounceOut);
        scaledownY=Actions.scaleBy(-0.3f,0.5f,.4f,Interpolation.bounceOut);
        scaleupXTahm=Actions.scaleBy(-0.5f,0.3f,.4f,Interpolation.bounceOut);
        scaledownXTahm=Actions.scaleBy(0.5f,-0.3f,.4f,Interpolation.bounceOut);
        scaleupYTahm=Actions.scaleBy(.3f,-0.5f,.4f,Interpolation.bounceOut);
        scaledownYTahm=Actions.scaleBy(-0.3f,0.5f,.4f,Interpolation.bounceOut);
        scaleupXRen=Actions.scaleBy(-0.5f,0.3f,.4f,Interpolation.bounceOut);
        scaledownXRen=Actions.scaleBy(0.5f,-0.3f,.4f,Interpolation.bounceOut);
        scaleupYRen=Actions.scaleBy(.3f,-0.5f,.4f,Interpolation.bounceOut);
        scaledownYRen=Actions.scaleBy(-0.3f,0.5f,.4f,Interpolation.bounceOut);
        scaleupXAnn= Actions.scaleBy(-0.5f,0.3f,.4f,Interpolation.bounceOut);
        scaledownXAnn=Actions.scaleBy(0.5f,-0.3f,.4f,Interpolation.bounceOut);
        scaleupYAnn=Actions.scaleBy(.3f,-0.5f,.4f,Interpolation.bounceOut);
        scaledownYAnn=Actions.scaleBy(-0.3f,0.5f,.4f,Interpolation.bounceOut);

        camera.setToOrtho(false, WIDTH / 2, HEIGHT / 2);
        overload = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.overload);
        // overload = new com.badlogic.gdx.scenes.scene2d.ui.Image(manager.game.manager.get("overloadpng.png",Texture.class));
        runepris = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.runepris);
        //runepris = new com.badlogic.gdx.scenes.scene2d.ui.Image(manager.game.manager.get("runeprispng.png",Texture.class));
        spellflux = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.spellflux);
        //spellflux = new com.badlogic.gdx.scenes.scene2d.ui.Image(manager.game.manager.get("spellfluxpng.png",Texture.class));
        realmwarp = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.realmwarp);
        //realmwarp = new com.badlogic.gdx.scenes.scene2d.ui.Image(manager.game.manager.get("realmwarppng.png",Texture.class));
        //realmcircle =  new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.realmcircle);
        overloadflux = new Image(Assets.overload);
        runeprisflux = new Image(Assets.runepris);

        bg = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.ryzeflip4);
        bg2 = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.ryzeflip4);
        bg3 = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.ryzeflip4);
        bg4 = new com.badlogic.gdx.scenes.scene2d.ui.Image(Assets.ryzeflip4);

        runeatlas.addRegion("particle",new TextureRegion(new Texture("particle.png")));
        burstatlas.addRegion("ryzetrail",new TextureRegion(new Texture("ryzetrail.png")));

        eye1 = new EyeParticle(runeatlas);
        eye2 = new EyeParticle(runeatlas);
        eye3 = new EyeParticle(runeatlas);
        eye4 = new EyeParticle(runeatlas);
        eye5 = new EyeParticle(runeatlas);
        eye6 = new EyeParticle(runeatlas);
        eye7 = new EyeParticle(runeatlas);
        eye8 = new EyeParticle(runeatlas);
        burst = new BurstParticle(burstatlas);

        eye1.getEmitter().getTint().setColors(tint);
        eye2.getEmitter().getTint().setColors(tint);
        eye3.getEmitter().getTint().setColors(tint);
        eye4.getEmitter().getTint().setColors(tint);
        eye5.getEmitter().getTint().setColors(tint);
        eye6.getEmitter().getTint().setColors(tint);
        eye7.getEmitter().getTint().setColors(tint);
        eye8.getEmitter().getTint().setColors(tint);
        eye1.setX(Gdx.graphics.getWidth()/2+65);
        eye1.setY(Gdx.graphics.getHeight()/2+360);
        eye2.setX(Gdx.graphics.getWidth()/2+75);
        eye2.setY(Gdx.graphics.getHeight()/2+280);
        eye2.flipXY();
        eye3.setX(Gdx.graphics.getWidth()/2+65);
        eye3.setY(Gdx.graphics.getHeight()/2+360);
        eye4.setX(Gdx.graphics.getWidth()/2+75);
        eye4.setY(Gdx.graphics.getHeight()/2+280);
        eye4.flipXY();
        eye5.setX(Gdx.graphics.getWidth()/2+65);
        eye5.setY(Gdx.graphics.getHeight()/2+360);
        eye6.setX(Gdx.graphics.getWidth()/2+75);
        eye6.setY(Gdx.graphics.getHeight()/2+280);
        eye6.flipXY();
        eye7.setX(Gdx.graphics.getWidth()/2+65);
        eye7.setY(Gdx.graphics.getHeight()/2+360);
        eye8.setX(Gdx.graphics.getWidth()/2+75);
        eye8.setY(Gdx.graphics.getHeight()/2+280);
        eye8.flipXY();

        burst.setVisible(false);
        burst.setSize(200,200);
        burst.getEmitter().getTint().setColors(bursttint);


        //Animations
        fluxatlas.addRegion("cursormiddle",new TextureRegion(new Texture("cursormiddle.png")));
        //knobatlas.addRegion("icon16",new TextureRegion(new Texture("icon16.png")));
        fluxer = new FluxParticle(fluxatlas);
        overfluxer = new FluxParticle(fluxatlas);
        runefluxer = new FluxParticle(fluxatlas);
        //knob = new KnobParticle(knobatlas);

        tahmbreak = new AnimatedImage(Assets.tahmanim);
        runetrap = new AnimatedImage(Assets.prisanim);
        runetrapflux = new AnimatedImage(Assets.prisanim);
        loader = new AnimatedImage(Assets.loaderanim);
        realmcircle = new AnimatedImage(Assets.realmanim);
        tahmbubble = new AnimatedImage2(Assets.tahmbubble);
        talondags = new AnimatedImage2(Assets.talondags);
        talonult = new AnimatedImage2(Assets.talonult);
        lightbeam = new AnimatedImage2(Assets.lightbeam);
        aura = new AnimatedImage2(Assets.aura);
        claw = new AnimatedImage2(Assets.claw);
        tahmult = new AnimatedImage2(Assets.tahmult);
        ashearrow = new AnimatedImage2(Assets.ashearrow);

        cursor = new Rectangle(60, 100, 0, 0);

        stage = new Stage();
        stage2 = new Stage();
        stage3 = new Stage();
        stage4 = new Stage();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        velocity = new Vector3(0, 0, 0);
        this.manager = manager;
        delta = Gdx.graphics.getDeltaTime();

        screenBounds = new Rectangle(0,0,WIDTH*2,HEIGHT*2);

        bg.setBounds(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        runePrisInit();
        spellFluxInit();
        realmWarpInit();

        multi = new InputMultiplexer();
        list = new MultiGestureDetector(new Glist(stage4,talonwarp.actorTalon(),tahmwarp.actorTahm(),ren.actorRen(),ann.actorAnn()));

        multi.addProcessor(stage);
        multi.addProcessor(stage2);
        multi.addProcessor(stage3);
        multi.addProcessor(stage4);
        //multi.addProcessor(list);

        Gdx.input.setInputProcessor(multi);
        overInput();

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               nextPhase();
                           }
                       }
                , 0f        //    (delay)
                , 3.5f     //    (seconds)
        );

        overload.setPosition(WIDTH+20,HEIGHT,Align.center);
        overload.setSize(200,200);
        overBounds = new Rectangle(overload.getX(),overload.getY(),overload.getWidth(),overload.getHeight());
        bubbleBounds = new Rectangle();
        overload.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
        tahm = new TahmK(WIDTH, HEIGHT*2,Align.center,200,200);
        tahm.actorTahm().setVisible(false);
        tahm.actorTahm().addAction(Actions.alpha(0));

        tahmbubble.setVisible(false);
        tahm.actorTahm().addAction(Actions.sequence(Actions.alpha(0)));
        tahmbubble.setSize(300,300);
        tahmult.setSize(450,450);
        //tahmult.setPosition(tahm.actorTahm().getX()-50,tahm.actorTahm().getY()-50);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                eye1.getEffect().scaleEffect(2f);
                eye2.getEffect().scaleEffect(2f);
            }
        },2f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                eye1.getEffect().scaleEffect(.5f);
                eye2.getEffect().scaleEffect(.5f);
            }
        },2.5f);

        stage.addActor(bg);
        stage.addActor(eye1);
        stage.addActor(eye2);
        stage.addActor(burst);
        stage.addActor(tahmult);
        stage.addActor(overload);
        stage.addActor(tahm.actorTahm());
        stage.addActor(tahmbubble);


        System.out.println(prefs.getLong("crystals"));
        System.out.println(prefs.getLong("crystals2"));
        System.out.println(prefs.getLong("crystals3"));
        System.out.println(prefs.getLong("crystals4"));
    }

    public void runePrisInit(){

        stage2.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
        bg2.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        runepris.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f)));
        runepris.setPosition(WIDTH,100,Align.center);
        runepris.setSize(200,200);
        runepris.setOriginX(runepris.getImageWidth()/2);
        runepris.setOriginY(runepris.getImageHeight()/2);

        talonwall1 = new Image(Assets.talonwall1);
        talonwall2 = new Image(Assets.talonwall2);
        mountainBody();

        crystals2 = prefs.getLong("crystals2",0);

        talon = new Talon(WIDTH,1600,Align.center, 200,200);
        talon.actorTalon().setOrigin(talon.actorTalon().getImageWidth()/2,talon.actorTalon().getImageHeight()/2);
        talon.actorTalon().addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1.5f)));

        talondags.setSize(500,300);
        talondags.setVisible(false);
        talondags.setOriginX(talondags.getWidth()/2);
        talondags.setOriginY(talondags.getHeight()/2);
        talondags.setRotation(180f);
        //talondags.setPosition(screenBounds.getX()-1000,screenBounds.getY());
        //talondags.getAnimation().setPlayMode(Animation.PlayMode.NORMAL);
        runeInput();
        ultSet();

        stage2.addActor(bg2);
        stage2.addActor(eye3);
        stage2.addActor(eye4);
        stage2.addActor(talondags);
        stage2.addActor(talonult);
        stage2.addActor(talon.actorTalon());
        stage2.addActor(talonwall1);
        stage2.addActor(talonwall2);
        stage2.addActor(runepris);

    }

    public void spellFluxInit(){
        bg3.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spellflux.setPosition(WIDTH,100,Align.center);
        spellflux.setSize(200,200);
        overloadflux.setSize(200,200);
        overloadflux.setPosition(spellflux.getX()+spellflux.getWidth(),spellflux.getY());
        runeprisflux.setSize(200,200);
        runeprisflux.setPosition(spellflux.getX()-spellflux.getWidth(),spellflux.getY());

        ryze = new Image(Assets.ryze);
        ryze.setSize(200,200);
        ryze.setPosition(WIDTH-30,HEIGHT*2-25);
        ryze.addAction(Actions.alpha(0));

        lightbeam.setSize(1200,1200);
        lightbeam.setPosition(WIDTH-500,HEIGHT*2-100);
        aura.setSize(400,400);
        aura.setPosition(ryze.getX()-100,ryze.getY()-100);
        aura.addAction(Actions.alpha(0));

        //knob.setX(loader.getX());
        //knob.setY(loader.getY());
        //knob.setSize(300,300);
        fluxer.setSize(200,200);
        overfluxer.setSize(200,200);
        overfluxer.getEmitter().getTint().setColors(fluxertint);
        runefluxer.setSize(200,200);
        runefluxer.getEmitter().getTint().setColors(fluxertint2);

        spellflux.addAction(Actions.sequence(Actions.fadeIn(2f)));
        progress();
        fluxInput();

        crystals3 = prefs.getLong("crystals3",0);

        stage3.addActor(bg3);
        stage3.addActor(eye5);
        stage3.addActor(eye6);
        stage3.addActor(overfluxer);
        //stage3.addActor(bar);
        stage3.addActor(ryze);
        stage3.addActor(loader);
        //stage3.addActor(knob);
        stage3.addActor(fluxer);
        stage3.addActor(aura);
        stage3.addActor(lightbeam);
        stage3.addActor(runefluxer);
        stage3.addActor(spellflux);
        stage3.addActor(overloadflux);
        stage3.addActor(runeprisflux);


    }

    public void realmWarpInit(){
        bg4.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        realmwarp.setPosition(WIDTH,100,Align.center);
        realmwarp.setSize(200,200);
        realmwarp.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));

        aurelion = new Image(Assets.aurelion);
        aurelion.setSize(200,200);
        aurelion.setPosition(WIDTH-30,HEIGHT+50);
        aurelion.addAction(Actions.alpha(0));

        rengar = new Image(Assets.rengar);
        rengar.setSize(200,200);
        rengar.setPosition(WIDTH-30,HEIGHT+50);
        rengar.addAction(Actions.alpha(0));
        rengoBounds = new Rectangle(rengar.getX(),rengar.getY(),rengar.getWidth(),rengar.getHeight());

        ashearrow.setSize(200,200);
        ashearrow.setPosition(screenBounds.getX()-200,screenBounds.getY());
        //arrowBounds = new Array<Rectangle>();
        arrowBounds = new Rectangle();

        claw.setSize(200,200);
        claw.setPosition(0,0);
        claw.addAction(Actions.alpha(0));

        q1 = new Rectangle(0,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        q2 = new Rectangle(0,q1.getHeight(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        q3 = new Rectangle(q1.getWidth(),0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        q4 = new Rectangle(q1.getWidth(),q1.getHeight(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        talonwarp = new Talon(q1.getX(),q1.getY(),Align.center,200,200);
        tahmwarp = new TahmK(q2.getX(),q2.getY()+q2.getHeight(),Align.center,200,200);
        ren = new Renek(q3.getX(),q3.getY(),Align.center,200,200);
        ann = new Annie(q4.getX(),q4.getY(),Align.center,200,200);

        tahmwarp.actorTahm().setPosition(q2.getX(),q2.getY()+q2.getHeight()-tahmwarp.actorTahm().getHeight());
        ren.actorRen().setPosition(q3.getX()+q3.getWidth()-ren.actorRen().getWidth(),q3.getY());
        ann.actorAnn().setPosition(q4.getX()+q4.getWidth()-ann.actorAnn().getWidth(),q4.getY()+q4.getHeight()-ann.actorAnn().getHeight());

        realmcircle.setPosition(WIDTH/2+50,HEIGHT/2+(realmcircle.getHeight()*3-100));
        realmcircle.setSize(500,500);
        realmbounds = new Rectangle(realmcircle.getX(),realmcircle.getY(),realmcircle.getWidth(),realmcircle.getHeight());
        realmcircle.addAction(Actions.sequence(Actions.fadeIn(2f)));

        warpBounds = new Rectangle(realmwarp.getX(),realmwarp.getY(),realmwarp.getWidth(),realmwarp.getHeight());

        crystals4 = prefs.getLong("crystals4",0);

        warpInput();

        stage4.addActor(bg4);
        stage4.addActor(eye7);
        stage4.addActor(eye8);
        stage4.addActor(realmcircle);
        stage4.addActor(talonwarp.actorTalon());
        stage4.addActor(tahmwarp.actorTahm());
        stage4.addActor(ren.actorRen());
        stage4.addActor(ann.actorAnn());
        stage4.addActor(aurelion);
        stage4.addActor(rengar);
        stage4.addActor(claw);
        stage4.addActor(ashearrow);
        stage4.addActor(realmwarp);

    }


    @Override
    public void dispose() {
        //stage.dispose();
        //stage2.dispose();
        //stage3.dispose();
        //stage4.dispose();
        Gdx.app.log(TAG, "disposed");
    }


    @Override
    public void handleInput(){
        if (Gdx.input.isTouched()) {
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if(realmbounds.contains(touchPos.x,touchPos.y)){
                System.out.println("Test");
            }

        }

        //Level1

        if(overload.getX() < screenBounds.getX()){
            //overload.setPosition(screenBounds.getX(),overload.getY());
            resetOver();
        }
        else if(overload.getX() > screenBounds.getX()+screenBounds.getWidth()){
            //overload.setPosition(screenBounds.getX()+screenBounds.getWidth(), overload.getY());
            resetOver();
        }
        if(overload.getY() < screenBounds.getY()){
            //overload.setPosition(overload.getX(),screenBounds.getY());
            resetOver();
        }
        else if(overload.getY() > screenBounds.getY() + screenBounds.getHeight()){
            //overload.setPosition(overload.getX(),screenBounds.getY() + screenBounds.getHeight());
            resetOver();
        }

        if(talonwarp.actorTalon().getX() <= q1.getX()){
            talonwarp.actorTalon().setPosition(q1.getX(),talonwarp.actorTalon().getY());

            if(flingXTalon <= -500){
                talonwarp.actorTalon().addAction(Actions.sequence(scaleupX,scaledownX));
                flingXTalon=0;
            }

        }

        //Level 2

        if(wallBounds.overlaps(prisBounds)){
            if(runepris.getX() < wallBounds.getX()){
                //runepris.setPosition(wallBounds.getX()+wallBounds.getWidth(),runepris.getY());
                //resetOver();
            }
            else if(runepris.getX() > wallBounds.getX()+wallBounds.getWidth()){
                //runepris.setPosition(wallBounds.getX()+wallBounds.getWidth(), runepris.getY());
                //resetOver();
            }
            if(runepris.getY() < wallBounds.getY()){
                //runepris.setPosition(wallBounds.getX(),wallBounds.getY()-runepris.getHeight());
                //resetOver();
            }
            else if(runepris.getY() > wallBounds.getY() + wallBounds.getHeight()){
                //runepris.setPosition(runepris.getX(),wallBounds.getY() + wallBounds.getHeight());
                //resetOver();
            }
        }

        if(wallBounds2.overlaps(prisBounds)){
            if(runepris.getX() < wallBounds2.getX()){
                //runepris.setPosition(wallBounds2.getX()+wallBounds2.getWidth(),runepris.getY());
                //resetOver();
            }
            else if(runepris.getX() > wallBounds2.getX()+wallBounds2.getWidth()){
                //runepris.setPosition(wallBounds.getX()+wallBounds.getWidth(), runepris.getY());
                //resetOver();
            }
            if(runepris.getY() < wallBounds2.getY()){
                //runepris.setPosition(wallBounds2.getX(),wallBounds2.getY()-runepris.getHeight());
                //resetOver();
            }
            else if(runepris.getY() > wallBounds2.getY() + wallBounds2.getHeight()){
                //runepris.setPosition(runepris.getX(),wallBounds2.getY() + wallBounds2.getHeight());
                //resetOver();
            }
        }

        //Level 4
        else if(talonwarp.actorTalon().getX()+talonwarp.actorTalon().getWidth() >= q1.getX()+q1.getWidth()){
            talonwarp.actorTalon().setPosition(q1.getX()+q1.getWidth()-talonwarp.actorTalon().getWidth(), talonwarp.actorTalon().getY());

            if(flingXTalon >= 500){
                talonwarp.actorTalon().addAction(Actions.sequence(scaleupX,scaledownX));
                flingXTalon=0;
            }
        }
        if(talonwarp.actorTalon().getY() <= q1.getY()){
            talonwarp.actorTalon().setPosition(talonwarp.actorTalon().getX(),q1.getY());

            if(flingYTalon <= -500){
                talonwarp.actorTalon().addAction(Actions.sequence(scaleupY,scaledownY));
                flingYTalon=0;
            }
        }
        else if(talonwarp.actorTalon().getY()+talonwarp.actorTalon().getHeight() >= q1.getY() + q1.getHeight()){
            talonwarp.actorTalon().setPosition(talonwarp.actorTalon().getX(),q1.getY() + q1.getHeight() - talonwarp.actorTalon().getHeight());

            if(flingYTalon >= 500){
                talonwarp.actorTalon().addAction(Actions.sequence(scaleupY,scaledownY));
                flingYTalon=0;
            }
        }

        if(tahmwarp.actorTahm().getX() <= q2.getX()){
            tahmwarp.actorTahm().setPosition(q1.getX(),tahmwarp.actorTahm().getY());
            if(flingXTahm <= -500){
                tahmwarp.actorTahm().addAction(Actions.sequence(scaleupXTahm,scaledownXTahm));
                flingXTahm=0;
            }
        }
        else if(tahmwarp.actorTahm().getX()+tahmwarp.actorTahm().getWidth() >= q2.getX()+q2.getWidth()){
            tahmwarp.actorTahm().setPosition(q2.getX()+q2.getWidth() - tahmwarp.actorTahm().getWidth(), tahmwarp.actorTahm().getY());
            if(flingXTahm >= 500){
                tahmwarp.actorTahm().addAction(Actions.sequence(scaleupXTahm,scaledownXTahm));
                flingXTahm=0;
            }
        }
        if(tahmwarp.actorTahm().getY() <= q2.getY()){
            tahmwarp.actorTahm().setPosition(tahmwarp.actorTahm().getX(),q2.getY());
            if(flingYTahm <= -500){
                tahmwarp.actorTahm().addAction(Actions.sequence(scaleupYTahm,scaledownYTahm));
                flingYTahm=0;
            }
        }
        else if(tahmwarp.actorTahm().getY()+tahmwarp.actorTahm().getHeight() >= q2.getY() + q2.getHeight()){
            tahmwarp.actorTahm().setPosition(tahmwarp.actorTahm().getX(),q2.getY() + q2.getHeight() - tahmwarp.actorTahm().getHeight());
            if(flingYTahm >= 500){
                tahmwarp.actorTahm().addAction(Actions.sequence(scaleupYTahm,scaledownYTahm));
                flingYTahm=0;
            }
        }

        if(ren.actorRen().getX() <= q3.getX()){
            ren.actorRen().setPosition(q3.getX(),ren.actorRen().getY());
            if(flingXRen <= -500){
                ren.actorRen().addAction(Actions.sequence(scaleupXRen,scaledownXRen));
                flingXRen=0;
            }
        }
        else if(ren.actorRen().getX()+ren.actorRen().getWidth() >= q3.getX()+q3.getWidth()){
            ren.actorRen().setPosition(q3.getX()+q3.getWidth() - ren.actorRen().getWidth(), ren.actorRen().getY());
            if(flingXRen >= 500){
                ren.actorRen().addAction(Actions.sequence(scaleupXRen,scaledownXRen));
                flingXRen=0;
            }
        }
        if(ren.actorRen().getY() <= q3.getY()){
            ren.actorRen().setPosition(ren.actorRen().getX(),q3.getY());
            if(flingYRen <= -500){
                ren.actorRen().addAction(Actions.sequence(scaleupYRen,scaledownYRen));
                flingYRen=0;
            }
        }
        else if(ren.actorRen().getY()+ren.actorRen().getHeight() >= q3.getY() + q3.getHeight()){
            ren.actorRen().setPosition(ren.actorRen().getX(),q3.getY() + q3.getHeight() - ren.actorRen().getHeight());
            if(flingYRen >= 500){
                ren.actorRen().addAction(Actions.sequence(scaleupYRen,scaledownYRen));
                flingYRen=0;
            }
        }

        if(ann.actorAnn().getX() <= q4.getX()){
            ann.actorAnn().setPosition(q4.getX(),ann.actorAnn().getY());
            if(flingXAnn <= -500){
                ann.actorAnn().addAction(Actions.sequence(scaleupXAnn,scaledownXAnn));
                flingXAnn=0;
            }
        }
        else if(ann.actorAnn().getX()+ann.actorAnn().getWidth() >= q4.getX()+q4.getWidth()) {
            ann.actorAnn().setPosition(q4.getX() + q4.getWidth() - ann.actorAnn().getWidth(), ann.actorAnn().getY());
            if (flingXAnn >= 500) {
                ann.actorAnn().addAction(Actions.sequence(scaleupXAnn,scaledownXAnn));
                flingXAnn = 0;
            }
        }
        if(ann.actorAnn().getY() <= q4.getY()){
            ann.actorAnn().setPosition(ann.actorAnn().getX(),q4.getY());
            if(flingYAnn <= -500){
                ann.actorAnn().addAction(Actions.sequence(scaleupYAnn,scaledownYAnn));
                flingYAnn=0;
            }

        }
        else if(ann.actorAnn().getY()+ann.actorAnn().getHeight() >= q4.getY() + q4.getHeight()){
            ann.actorAnn().setPosition(ann.actorAnn().getX(),q4.getY() + q4.getHeight() - ann.actorAnn().getHeight());
            if(flingYAnn >= 500){
                ann.actorAnn().addAction(Actions.sequence(scaleupYAnn,scaledownYAnn));
                flingYAnn=0;
            }
        }

    }

    public void overInput(){
        overload.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                start = new Vector2(x, y);
                isTouched = true;
                alpha = overload.getColor().a;
                if (alpha == 1f){
                    overload.clearActions();
                }
                burst.setVisible(true);
                overload.setDrawable(new TextureRegionDrawable(Assets.overload2));
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                end = new Vector2(x,y);
                isTouched = false;
                //if(overload.getY()<=400) {
                burst.setVisible(false);
                //}
                overload.setDrawable(new TextureRegionDrawable(Assets.overload1));
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                overload.moveBy(x - overload.getWidth() / 2, y - overload.getHeight() / 2);
                if(overload.getY() <= HEIGHT-200 && isTouched){
                    overload.setY(HEIGHT-200);
                }
                else if(overload.getY() >= HEIGHT+200 && isTouched) {
                    overload.setY(HEIGHT+200);
                }
                if(overload.getX() <= WIDTH -150 && isTouched)
                {
                    overload.setX(WIDTH-150);
                }
                else if(overload.getX() >= WIDTH + 150 && isTouched){
                    overload.setX(WIDTH + 150);
                }

                if(x<0){
                    burst.getEmitter().getWind().setHigh(300);
                    burst.getEmitter().getGravity().setHigh(-200);
                }
                else if(x>0){
                    burst.getEmitter().getWind().setHigh(-300);
                    burst.getEmitter().getGravity().setHigh(-200);
                }
                if(y<0){
                    burst.getEmitter().getWind().setHigh(0);
                    burst.getEmitter().getGravity().setHigh(300);
                }
            }

        });

        overload.addListener(new ActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){

                overload.addAction(Actions.moveBy(x,y,3f));
                flinger = true;
                burst.setVisible(true);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        burst.setVisible(false);
                    }
                },3f);
                System.out.println(flingtime);
            }

        });
    }

    public void runeInput(){
        runepris.addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                start = new Vector2(x,y);
                runepris.setDrawable(new TextureRegionDrawable(Assets.runepris2));
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
                runepris.setDrawable(new TextureRegionDrawable(Assets.runepris1));
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                prisBounds = new Rectangle(runepris.getX(),runepris.getY(),runepris.getWidth(),runepris.getHeight());
                //if(!wallBounds2.overlaps(prisBounds)) {
                    //if(!wallBounds.overlaps(prisBounds)) {
                        runepris.moveBy(x - runepris.getWidth() / 2, y - runepris.getHeight() / 2);
                    //}
                //}
                runeprisX = x;
                runeprisY = y;



                Vector2 anglemaker = new Vector2(touchPos.x,touchPos.y);
                Vector2 talonangle = new Vector2(talondags.getX(),talondags.getY());
                float angle = anglemaker.angle();

                talondags.addAction(Actions.rotateBy(15f,1f));

                System.out.println(runeprisX);
                System.out.println(runeprisY);

            }

        });

        runepris.addListener(new ActorGestureListener() {

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                //super.tap(event, x, y, count, button);
                System.out.println(count);
                if(count == 2){
                    talon.trap(runepris);
                    stage2.addActor(runetrap);
                    runetrap.setPosition(talon.actorTalon().getX(),talon.actorTalon().getY());
                    runetrap.setSize(200,200);
                    talonchase = true;
                    if(!frozen){
                        talon.speedup(1f);
                        if(!frozen && talonchase){
                            music3.play();
                        }
                        crystals2=crystals2+75;
                        frozen=true;
                    }


                }

            }
        });


    }

    public void fluxInput(){
        spellflux.addListener(new ActorGestureListener(){

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                if(count>=1){
                    //for progressbar
                    //bar.setValue(bar.getValue() + 2);
                    //level1
                    //counter=counter+10;
                    //level2
                    tapEvent = true;
                    fluxpress = true;
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            tapEvent = false;
                        }
                    },1f);

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            fluxpress = false;
                        }
                    },4f);

                    //System.out.println(bar.getValue());
                    crystals3=crystals3+5;


                }
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button){
                fluxtap=true;
                fluxer.setVisible(true);
                spellflux.setDrawable(new TextureRegionDrawable(Assets.flux2));

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        fluxer.setVisible(false);
                        //reset the particle properly
                        fluxtap=false;
                    }
                },.3f);
                spellflux.setDrawable(new TextureRegionDrawable(Assets.flux1));
            }
        });
        overloadflux.addListener(new ActorGestureListener(){

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                if(count>=1){
                    //for progressbarf
                    //bar.setValue(bar.getValue() + 2);
                    if(fluxpress){
                        counter=counter+22;
                        fluxpress=false;
                    }
                    crystals3=crystals3+5;
                    if(tapEvent){
                        crystals3 = crystals3+200;
                    }



                }
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button){
                overfluxtap=true;
                overfluxer.setVisible(true);
                overloadflux.setDrawable(new TextureRegionDrawable(Assets.overload2));

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        overfluxer.setVisible(false);
                        //reset the particle properly
                        overfluxtap=false;
                    }
                },.3f);
                overloadflux.setDrawable(new TextureRegionDrawable(Assets.overload1));
            }
        });

        runeprisflux.addListener(new ActorGestureListener(){

            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                if(count>=1){
                    //for progressbar
                    //bar.setValue(bar.getValue() + 2);
                    if(fluxpress){
                        counter=counter+30;
                        fluxpress=false;
                    }
                    crystals3=crystals3+5;

                    if(tapEvent){
                        crystals3 = crystals3+300;
                    }
                    if(runeimpactstart) {
                        if (!stopTrap) {
                            runeTrap();
                            stopTrap = true;
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    stopTrap = false;
                                }
                            }, 5f);
                        }
                    }

                }
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button){
                runefluxtap=true;
                runefluxer.setVisible(true);
                runeprisflux.setDrawable(new TextureRegionDrawable(Assets.runepris2));

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        runefluxer.setVisible(false);
                        //reset the particle properly
                        runefluxtap=false;
                    }
                },.3f);
                runeprisflux.setDrawable(new TextureRegionDrawable(Assets.runepris1));
            }
        });
    }

    public void warpInput(){

        realmwarp.addListener(new DragListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //drawablerealmwarp
                realmwarp.setDrawable(new TextureRegionDrawable(Assets.realmwarp2));
                return true;
            }


            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //
                realmwarp.setPosition(WIDTH,100,Align.center);
                realmwarp.setTouchable(Touchable.disabled);
                if(warpBounds.contains(rengoBounds)){
                    rengoStop=true;
                }
                realmwarp.setDrawable(new TextureRegionDrawable(Assets.realmwarp1));

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        rengoStop=false;
                        rengar.setVisible(true);
                        realmwarp.setTouchable(Touchable.enabled);
                    }
                },3f);

            }


            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                realmwarp.moveBy(x - realmwarp.getWidth() / 2, y - realmwarp.getHeight() / 2);
            }


        });

        talonwarp.actorTalon().addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //
                talontouch=true;
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                talontouch=false;
                //
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                talontouch=true;
                talonwarp.actorTalon().moveBy(x - talonwarp.actorTalon().getWidth() / 2, y - talonwarp.actorTalon().getHeight() / 2);
            }

        });

        talonwarp.actorTalon().addListener(new MultiActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){
                flingforward = Actions.moveBy(x,y,2);

                talonwarp.actorTalon().addAction(flingforward);

                System.out.println("TALON MOVE X Y" + " " + x + " " + y);

                flingXTalon = x;
                flingYTalon = y;

                if(x>4600 || y > 4600){
                    bouncefinished=true;
                }

            }

        });

        tahmwarp.actorTahm().addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //
                tahmtouch=true;
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //
                tahmtouch=false;
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                tahmwarp.actorTahm().moveBy(x - tahmwarp.actorTahm().getWidth() / 2, y - tahmwarp.actorTahm().getHeight() / 2);
                tahmtouch=true;

            }

        });

        tahmwarp.actorTahm().addListener(new MultiActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){
                flingforward=Actions.moveBy(x,y,2);
                tahmwarp.actorTahm().addAction(flingforward);
                System.out.println("TALON MOVE X Y" + " " + x + " " + y);
                flingXTahm = x;
                flingYTahm = y;

                if(x>4600 || y > -4600){
                    bouncefinished2=true;
                }
            }

        });

        ren.actorRen().addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //
                rentouch=true;
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //
                rentouch=false;
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                ren.actorRen().moveBy(x - ren.actorRen().getWidth() / 2, y - ren.actorRen().getHeight() / 2);
                rentouch=true;

            }

        });

        ren.actorRen().addListener(new MultiActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){
                flingforward=Actions.moveBy(x,y,2);
                ren.actorRen().addAction(flingforward);
                System.out.println("TALON MOVE X Y" + " " + x + " " + y);
                flingXRen = x;
                flingYRen = y;

                if(x<-4600 || y>4600){
                    bouncefinished3=true;
                }
            }

        });

        ann.actorAnn().addListener(new DragListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //
                anntouch=true;
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //
                anntouch=false;
            }

            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                ann.actorAnn().moveBy(x - ann.actorAnn().getWidth() / 2, y - ann.actorAnn().getHeight() / 2);
                anntouch=true;

            }

        });

        ann.actorAnn().addListener(new MultiActorGestureListener() {
            public void fling (InputEvent event, float x, float y, int button){
                flingforward=Actions.moveBy(x,y,2);
                ann.actorAnn().addAction(flingforward);
                System.out.println("TALON MOVE X Y" + " " + x + " " + y);
                flingXAnn = x;
                flingYAnn = y;

                if(x<-4600 || y<-4600){
                    bouncefinished4=true;
                }

            }

        });

    }

    public void draw() {

        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!level2 && !level3 && !level4){
            stage.draw();
            stage.act(delta);
            overloadTime+=Gdx.graphics.getDeltaTime();
            //tahmult.setPosition(tahm.actorTahm().getX()-75,tahm.actorTahm().getY()-75);
        }


        if(level2){
            stage2.draw();
            runetrap.act(delta);
            runepris.act(delta);
            bg2.act(delta);
            eye3.act(delta);
            eye4.act(delta);
            talondags.act(delta);
            talonult.setPosition(talon.actorTalon().getX()-300,talon.actorTalon().getY()-300);
            //if(usetalonult && !frozen){
            talonult.act(delta);
            //}
            if(hit1 && !scaleonce1){
                eye3.getEffect().scaleEffect(scaleeff);
                eye4.getEffect().scaleEffect(scaleeff);
                scaleonce1=true;
                scaleeff=scaleeff+1;
            } else if(!scaleonce1){
                eye3.getEffect().scaleEffect(scaleeff);
                eye4.getEffect().scaleEffect(scaleeff);
                scaleonce1=true;
            }
            if(elapsedTimeRune<=180 && level2) {
                elapsedTimeRune += Gdx.graphics.getDeltaTime();
                crystallize=Math.round(elapsedTimeRune);
            }
            if(elapsedTimeRune>=12){
                hit2=true;
            }
            if(elapsedTimeRune>= 20 && !tapcancel){
                talon.speedup(1f);
                music3.play();
                tapcancel=true;
            }
            else if(elapsedTimeRune >=50 && !tapcancel2){
                talon.speedup(1f);
                music3.play();
                tapcancel2=true;
            }
            else if(elapsedTimeRune >=80 && !tapcancel3){
                talon.speedup(1f);
                music3.play();
                tapcancel3=true;
            }
            else if(elapsedTimeRune >=110 && !tapcancel4){
                talon.speedup(1f);
                music3.play();
                tapcancel4=true;
            }
            if(!talonchase) {
                talon.chase(runepris);
                talon.jump(runepris);
                talon.rake(runepris);
                talon.actorTalon().act(delta);
                //gettheact
            }

            if(talon.talonBounds().overlaps(wallBounds)&&!talonscale){
                talon.actorTalon().addAction(Actions.sequence(Actions.scaleBy(.5f,.5f,.5f,Interpolation.exp10In),Actions.scaleBy(-.5f,-.5f,1f,Interpolation.exp10In),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        talon.speedup(.5f);
                    }
                })));
                talonscale=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        talonscale=false;
                    }
                },8f);
            }
            if(talon.talonBounds().overlaps(wallBounds2)&&!talonscale2){
                talon.actorTalon().addAction(Actions.sequence(Actions.scaleBy(.5f,.5f,1f,Interpolation.exp10In),Actions.scaleBy(-.5f,-.5f,1f,Interpolation.exp10In),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        talon.speedup(.5f);
                    }
                })));
                talonscale2=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        talonscale2=false;
                    }
                },8f);

            }

        }

        if(level3) {
            stage3.draw();
            spellflux.act(delta);
            loader.act2(delta,counter);
            eye5.act(delta);
            eye6.act(delta);
            overfluxer.setX(overloadflux.getX()+50);
            overfluxer.setY(overloadflux.getY()+100);
            runefluxer.setX(runeprisflux.getX()+50);
            runefluxer.setY(runeprisflux.getY()+100);
            fluxer.setX(spellflux.getX()+50);
            fluxer.setY(spellflux.getY()+100);
            if(!swapOnce) {
                swapFlux();
                swapOnce=true;
            }
            overloadflux.act(delta);
            runeprisflux.act(delta);
            runetrapflux.act(delta);
            if(hit2 && !scaleonce2){
                eye5.getEffect().scaleEffect(scaleeff);
                eye6.getEffect().scaleEffect(scaleeff);
                scaleonce2 = true;
                scaleeff=scaleeff+1;
            } else if(!scaleonce2){
                eye5.getEffect().scaleEffect(scaleeff);
                eye6.getEffect().scaleEffect(scaleeff);
                scaleonce2=true;
            }
            if(fluxtap) {
                fluxer.act(delta);
            }
            if(overfluxtap){
                overfluxer.act(delta);
            }
            if(runefluxtap){
                runefluxer.act(delta);
            }
            bg3.act(delta);
            if(!useonce){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        lightbeam.act(delta);
                    }
                },2f);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        lightuse=true;
                        lightbeam.addAction(Actions.alpha(0));
                    }
                },3.1f);
                //useonce=true;
            }
            aura.act(delta);
            ryze.act(delta);
            // knob.act(delta);

        }

        if(level4) {
            stage4.draw();
            randomReng = MathUtils.random(0,4);
            warpBounds.set(realmwarp.getX()-25,realmwarp.getY()-25,realmwarp.getWidth()+50,realmwarp.getHeight()+50);
            rengoBounds.set(rengar.getX(),rengar.getY(),rengar.getWidth(),rengar.getHeight());
            arrowBounds.set(ashearrow.getX(),ashearrow.getY(),ashearrow.getWidth(),ashearrow.getHeight());
            if(!fireArrowOnce) {
                fireArrow();
                fireArrowOnce=true;
            }

            if(randomReng==0 && !aureltouch1 && !rengoStop){
                leapStrike(talonwarp.actorTalon());

            }
            else if(randomReng==1 && !aureltouch2 && !rengoStop){
                leapStrike(tahmwarp.actorTahm());
            }
            else if(randomReng==3 && !aureltouch3 && !rengoStop){
                leapStrike(ren.actorRen());
            }
            else if(randomReng==4 && !aureltouch4 && !rengoStop){
                leapStrike(ann.actorAnn());
            }

            if(level4 && !timersonce) {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        random = MathUtils.random(1, 10);
                        timersonce = true;
                        changePlaces();
                    }
                }, 0, 4);
            }
            if(rengoStop){
                rengar.setPosition(WIDTH-30,HEIGHT+50);
                rengar.setVisible(false);
                rengoBounds.setPosition(rengar.getX(),rengar.getY());
                claw.setPosition(rengar.getX(),rengar.getY());

            }

            if(arrowBounds.overlaps(talon.talonBounds())&&!arrowstop1){
                talonwarp.actorTalon().setTouchable(Touchable.disabled);
                arrowReset();
                arrowstop1=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                      talonwarp.actorTalon().setTouchable(Touchable.enabled);
                    }
                },2f);

            }
            if(arrowBounds.overlaps(tahmwarp.tahmBounds())&&!arrowstop2){
                tahmwarp.actorTahm().setTouchable(Touchable.disabled);
                arrowReset();
                arrowstop2=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        tahm.actorTahm().setTouchable(Touchable.enabled);
                    }
                },2f);
            }
            if(arrowBounds.overlaps(ren.renBounds())&&!arrowstop3){
                ren.actorRen().setTouchable(Touchable.disabled);
                arrowReset();
                arrowstop3=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        ren.actorRen().setTouchable(Touchable.enabled);
                    }
                },2f);
            }
            if(arrowBounds.overlaps(ann.annBounds())&&!arrowstop4){
                ann.actorAnn().setTouchable(Touchable.disabled);
                arrowReset();
                arrowstop4=true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        ann.actorAnn().setTouchable(Touchable.enabled);
                    }
                },2f);
            }
            random = MathUtils.random(1,4);
            //stage4.act(delta);
            bg4.act(delta);
            eye7.act(delta);
            eye8.act(delta);
            aurelion.act(delta);
            rengar.act(delta);
            claw.act(delta);
            ashearrow.act(delta);
            if(hit3 && !scaleonce3){
                eye7.getEffect().scaleEffect(scaleeff);
                eye8.getEffect().scaleEffect(scaleeff);
                scaleonce3=true;
            } else if(!scaleonce3){
                eye7.getEffect().scaleEffect(scaleeff);
                eye8.getEffect().scaleEffect(scaleeff);
                scaleonce3=true;
            }
            realmcircle.act(delta);

            //if(!inside1 && !bouncefinished){
                talonwarp.actorTalon().act(delta);
            //}
            //if(!inside2 && !bouncefinished2){
                tahmwarp.actorTahm().act(delta);
            //}
            //if(!inside3 && !bouncefinished3){
                ren.actorRen().act(delta);
            //}
            //if(!inside4 && !bouncefinished4){
                ann.actorAnn().act(delta);
            //}
        }
    }

    @Override
    public void update(float dt) {
        //overBounds = new Rectangle(overload.getX(),overload.getY(),overload.getWidth(),overload.getHeight());
        if(tahmbubble.isVisible()) {
            //bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
            //bubbleBounds.fitOutside(tahm.tahmBounds());
            bubbleBounds.setPosition(tahmbubble.getX(), tahmbubble.getY());
        }
        else if (!tahmbubble.isVisible()){
            bubbleBounds.setPosition(screenBounds.getX()-1000,screenBounds.getY());
        }
        overBounds.setPosition(overload.getX(),overload.getY());
        talondags.setPosition(talon.actorTalon().getX()-30,talon.actorTalon().getY()-50);
        burst.setX(overload.getX()-150);
        burst.setY(overload.getY()-200);
        burst.act(delta);
        /*if(overload.getY() > 400){
            overload.setTouchable(Touchable.disabled);
        }*/

        tahmbubble.setPosition(tahm.actorTahm().getX()-50,tahm.actorTahm().getY()-50);
        tahm.update(dt);
        talon.update(dt);
        tahmwarp.update(dt);
        talonwarp.update(dt);
        ren.update(dt);
        ann.update(dt);
        handleInput();
        camera.update();
        //LEVEL 1 END
        if(flinger && overload.getY() > 400){
            flingtime+=Gdx.graphics.getDeltaTime();
        }
        if(playwice && !flingonce){
            flingtime=0;
            flingonce=true;
        }

        if(overBounds.overlaps(bubbleBounds)){
            resetOver();
            bubblecount=bubblecount+1;
            //tahm.actorTahm().addAction(Actions.moveTo(screenBounds.getX(),screenBounds.getY(),.2f,Interpolation.circleOut));
            if(bubblecount==2) {
                tahmbubble.setVisible(false);
            }
            System.out.println("Hi");
        }
        if(tahm.tahmBounds().overlaps(overBounds) && !isTouched && !stopCol) {
            crystals = crystals + 400;
            prefs.putLong("crystals", crystals);
            prefs.flush();
            System.out.println(prefs.getLong("crystals"));
            stopCol = true;
            if (overloadTime <= 10){
                hit1 = true;
            }
            if(!fizzle1){
                fizzle();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run(){
                        //if over doesnt hit the mark
                        level2 = true;
                        music.dispose();
                        bg2.addAction(Actions.fadeIn(2f));
                    }
                },3f,0f,1);
                fizzle1=true;
            }
        }
        if(overcount==4){
            if(!fizzle2) {
                fizzle();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run(){
                        //if over doesnt hit the mark
                        level2 = true;
                        music.dispose();
                        bg2.addAction(Actions.fadeIn(2f));
                    }
                },3f,0,1);
            }
            fizzle2=true;
        }
        /*if(overload.getY() >= screenBounds.getHeight()){
            if(playwice){
                if(!playonce) {
                    stage.addAction(Actions.fadeOut(3f));
                    overload.clearActions();
                    tahm.actorTahm().clearActions();

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            //if over doesnt hit the mark
                            //stage.dispose();
                            level2 = true;
                            bg2.addAction(Actions.fadeIn(2f));
                        }
                    }, 3f);

                    playonce = true;
                    playwice = false;
                }
            }
            if(!playwice){
                resetOver();
                playwice = true;
            }

        }*/

        /*if(overload.getY() > 410 && flingtime > 4 && overload.getY() < tahm.actorTahm().getY() && !playwice) {
            overload.addAction(Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    resetOver();
                    playwice=true;
                }
            })));
        }*/

       /* if(overload.getY() > 410 && flingtime > 4 && overload.getY() < tahm.actorTahm().getY() && playwice){
            if(playwice){
                stage.addAction(Actions.fadeOut(3f));
                overload.clearActions();
                tahm.actorTahm().clearActions();
                playonce = true;
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run(){
                        //if over doesnt hit the mark
                        //stage.dispose();
                        level2 = true;
                        bg2.addAction(Actions.fadeIn(2f));
                    }
                },3f);
            }
        }*/

        if(talonchase) {
            Timer.schedule(new Timer.Task(){
                @Override
                public void run(){
                    talon.actorTalon().clear();
                    talonchase = false;
                    frozen=false;
                    runetrap.remove();
                }
            },2f);
        }
        //LEVEL 2 END
        if(!blooddrawn) {
            caught();
        }
        //LEVEL 3 END
        if(level3 && !barmax) {
            tappedOut();
            if(loader.getY()>screenBounds.getHeight()){
                barmax=true;
                spellflux.addAction(Actions.fadeOut(2f));
                overloadflux.addAction(Actions.fadeOut(2f));
                runeprisflux.addAction(Actions.fadeOut(2f));
                ryze.addAction(Actions.fadeOut(2f));
                aura.addAction(Actions.fadeOut(2f));
                //bar.addAction(Actions.fadeOut(2f));
                //loader.act(delta);
                loader.addAction(Actions.sequence(Actions.fadeOut(1f),Actions.hide(),Actions.removeActor()));
                //either stage or bg
                bg3.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run(){
                        //if over doesnt hit the mark
                        level3=false;
                        stage3.clear();
                        stage3.dispose();
                        music4.dispose();
                        stage4.addAction(Actions.sequence(Actions.fadeIn(2f)));
                        level4=true;


                    }
                },3f);
            }
        }

        //Start timer for how long tapping takes
        if(level3){
            elapsedTime += Gdx.graphics.getDeltaTime();
            crystallize=Math.round(elapsedTime);
            if(!ryzecome){
                ryzeEnter();
            }
            if(!loadermove){
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        runeRotate();
                    }
                },3f);
                runeImpact();
                loadermove=true;
            }


            //set standard time to complete and increase score accordingly
        }

        if(level4) {
            bounceBack();
            elapsedTimeWarp+=Gdx.graphics.getDeltaTime();
            bouncefinish();
        }
    }

    @Override
    public void render() {
        delta = Gdx.graphics.getDeltaTime();
        // Interpolate the percentage to
        //make it more smooth
        draw();
        /*renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.rect(WIDTH-150,HEIGHT-200,WIDTH,HEIGHT-200);
        renderer.end();*/
        //System.out.println(playwice);
        //System.out.println(playonce);
        //System.out.println(flingtime);
        drawDebug();
    }

    public void drawDebug(){
        debugRenderer.setAutoShapeType(true);
        debugRenderer.begin();
        //debugRenderer.rect(WIDTH-150,HEIGHT-200,WIDTH,HEIGHT-200);
        //debugRenderer.rect(bubbleBounds.getX(),bubbleBounds.getY(),bubbleBounds.getWidth(),bubbleBounds.getHeight());
        //debugRenderer.rect(overBounds.getX(),overBounds.getY(),overBounds.getWidth(),overBounds.getHeight());
        //debugRenderer.rect(tahm.tahmBounds().getX(),tahm.tahmBounds().getY(),tahm.tahmBounds().getWidth(),tahm.tahmBounds().getHeight());
        //debugRenderer.rect(loader.getX(),loader.getY(),loader.getWidth(),loader.getHeight(),Color.CORAL,Color.BLUE,Color.WHITE,Color.BROWN);
        //debugRenderer.rect(wallBounds.getX(),wallBounds.getY(),wallBounds.getWidth(),wallBounds.getHeight());
        //debugRenderer.rect(wallBounds2.getX(),wallBounds2.getY(),wallBounds2.getWidth(),wallBounds2.getHeight());
        //debugRenderer.rect(prisBounds.getX(),prisBounds.getY(),prisBounds.getWidth(),prisBounds.getHeight());
        debugRenderer.rect(rengoBounds.getX(),rengoBounds.getY(),rengoBounds.getWidth(),rengoBounds.getHeight());
        debugRenderer.rect(warpBounds.getX(),warpBounds.getY(),warpBounds.getWidth(),warpBounds.getHeight());
        debugRenderer.rect(arrowBounds.getX(),arrowBounds.getY(),arrowBounds.getWidth(),arrowBounds.getHeight());
        debugRenderer.rect(talonwarp.talonBounds().getX(),talonwarp.talonBounds().getY(),talonwarp.talonBounds().getWidth(),talonwarp.talonBounds().getHeight());
        //debugRenderer2.render(world, stage.getCamera().combined);
        //debugRenderer.polygon();
        debugRenderer.end();
    }

    public void nextPhase(){
        //int num = getRandArrayElement(spot);
        int num = 0;
        if(num==0) {
            //target.setPosition(WIDTH, 600,Align.center);

            /*MoveToAction moveleft = new MoveToAction();
            moveleft.setPosition(screenBounds.getX()+50,HEIGHT*2,Align.center);
            moveleft.setDuration(0.7f);
            moveleft.setInterpolation(Interpolation.swing);

            MoveToAction moveleft2 = new MoveToAction();
            moveleft2.setPosition(screenBounds.getX()+50,screenBounds.getY()+1200,Align.center);
            moveleft2.setDuration(1.2f);
            moveleft2.setInterpolation(Interpolation.exp10);

            MoveToAction movedown = new MoveToAction();
            movedown.setPosition(screenBounds.getX()+50,screenBounds.getY()+50,Align.center);
            movedown.setDuration(0.7f);
            movedown.setInterpolation(Interpolation.swing);

            MoveToAction moveleft3 = new MoveToAction();
            moveleft3.setPosition(screenBounds.getX()+50,screenBounds.getY()+1600,Align.center);
            moveleft3.setDuration(1.2f);
            moveleft3.setInterpolation(Interpolation.exp10);

            MoveByAction twiddle = new MoveByAction();
            twiddle.setAmount(300,300);
            twiddle.setDuration(1.2f);
            twiddle.setInterpolation(Interpolation.elasticIn);


            MoveToAction moveright = new MoveToAction();
            moveright.setPosition(screenBounds.getWidth()+50,screenBounds.getY()+50,Align.center);
            moveright.setDuration(0.7f);
            moveright.setInterpolation(Interpolation.swing);

            MoveToAction moveup = new MoveToAction();
            moveup.setPosition(screenBounds.getWidth()+50,screenBounds.getHeight()+50,Align.center);
            moveup.setDuration(0.7f);
            moveup.setInterpolation(Interpolation.swing);

            MoveToAction moveright = new MoveToAction();
            moveright.setPosition(screenBounds.getWidth(), screenBounds.getY()+1000,Align.center);
            moveright.setDuration(1.2f);
            moveright.setInterpolation(Interpolation.exp10);

            MoveToAction moveright2 = new MoveToAction();
            moveright2.setPosition(screenBounds.getWidth(), screenBounds.getY()+1500,Align.center);
            moveright2.setDuration(1.2f);
            moveright2.setInterpolation(Interpolation.exp10);

            MoveToAction moveright3 = new MoveToAction();
            moveright3.setPosition(screenBounds.getWidth(), screenBounds.getY()+1700,Align.center);
            moveright3.setDuration(1.2f);
            moveright3.setInterpolation(Interpolation.exp10);


            SequenceAction seq = new SequenceAction(Actions.parallel(moveleft,Actions.run(new Runnable() {
                @Override
                public void run() {
                    tahmbubble.setVisible(true);
                }
            })),Actions.delay(.7f),movedown,moveright,Actions.run(new Runnable() {
                @Override
                public void run() {
                    tahmbubble.setVisible(true);
                }
            }));
            seq.addAction(Actions.delay(.7f));
            seq.addAction(Actions.parallel(moveup));

            RepeatAction logmove = new RepeatAction();
            logmove.setAction(seq);
            logmove.setCount(RepeatAction.FOREVER);

            tahm.actorTahm().addAction(logmove);*/
            //Vector2 randomPos = new Vector2();
            tahm.actorTahm().setOrigin(tahm.actorTahm().getImageWidth(),tahm.actorTahm().getHeight());
            final Rectangle rndBounds = new Rectangle(MathUtils.random(screenBounds.getX(),screenBounds.getWidth()),MathUtils.random(screenBounds.getY(),screenBounds.getHeight()),tahm.actorTahm().getWidth(),tahm.actorTahm().getHeight());
            Rectangle shootBounds = new Rectangle(WIDTH - 150, HEIGHT - 200, WIDTH, HEIGHT - 200);
            Vector2 shootXY = new Vector2(shootBounds.getX(), shootBounds.getY());
            Vector2 shootHW = new Vector2(shootBounds.getWidth(), shootBounds.getHeight());
            tahm.actorTahm().setVisible(false);
            tahm.tahmBounds().setSize(0,0);
            bubbleBounds.setSize(0,0);
            tahm.actorTahm().addAction(Actions.alpha(0));
            tahmbubble.addAction(Actions.alpha(0));
            bubblecount=0f;
            if (!shootBounds.contains(rndBounds) && !rndBounds.overlaps(shootBounds)) {
                    //tahm.actorTahm().setPosition(rndBounds.x,rndBounds.y);
                    tahmult.addAction(Actions.sequence(Actions.moveTo(rndBounds.x-125,rndBounds.y-125,1.3f,Interpolation.sineIn),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            tahm.actorTahm().setPosition(rndBounds.x,rndBounds.y);
                            tahm.actorTahm().addAction(Actions.fadeIn(.7f));
                            tahm.actorTahm().setVisible(true);
                            tahmbubble.setVisible(true);
                            tahmbubble.addAction(Actions.fadeIn(.7f));
                            tahm.tahmBounds().setSize(tahm.getWidth(),tahm.getHeight());
                            bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
                        }
                    })));
            }else{
                if (MathUtils.random(0, 3)==0) {
                    tahm.actorTahm().setPosition(screenBounds.getX()+50,screenBounds.getY()+50);
                    tahmult.addAction(Actions.sequence(Actions.moveTo(tahm.actorTahm().getX()-125,tahm.actorTahm().getY()-125,1.3f,Interpolation.sineIn),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            tahm.actorTahm().addAction(Actions.fadeIn(1f));
                            tahm.actorTahm().setVisible(true);
                            tahmbubble.setVisible(true);
                            tahmbubble.addAction(Actions.fadeIn(1f));
                            tahm.tahmBounds().setSize(tahm.getWidth(),tahm.getHeight());
                            bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
                        }
                    })));
                }
                else if(MathUtils.random(0,3)==1){
                    tahm.actorTahm().setPosition(screenBounds.getWidth()-50,screenBounds.getY()+50);
                    tahmult.addAction(Actions.sequence(Actions.moveTo(tahm.actorTahm().getX()-125,tahm.actorTahm().getY()-125,1.3f,Interpolation.sineIn),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            tahm.actorTahm().addAction(Actions.fadeIn(1f));
                            tahm.actorTahm().setVisible(true);
                            tahmbubble.setVisible(true);
                            tahmbubble.addAction(Actions.fadeIn(1f));
                            tahm.tahmBounds().setSize(tahm.getWidth(),tahm.getHeight());
                            bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
                        }
                    })));
                }
                else if(MathUtils.random(0,3)==2){
                    tahm.actorTahm().setPosition(screenBounds.getX()+50,screenBounds.getHeight()-50);
                    tahmult.addAction(Actions.sequence(Actions.moveTo(tahm.actorTahm().getX()-125,tahm.actorTahm().getY()-125,1.3f,Interpolation.sineIn),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            tahm.actorTahm().addAction(Actions.fadeIn(1f));
                            tahm.actorTahm().setVisible(true);
                            tahmbubble.setVisible(true);
                            tahmbubble.addAction(Actions.fadeIn(1f));
                            tahm.tahmBounds().setSize(tahm.getWidth(),tahm.getHeight());
                            bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
                        }
                    })));
                }
                else if(MathUtils.random(0,3)==3){
                    tahm.actorTahm().setPosition(screenBounds.getWidth()-50,screenBounds.getHeight()-50);
                    tahmult.addAction(Actions.sequence(Actions.moveTo(tahm.actorTahm().getX()-125,tahm.actorTahm().getY()-125,1.3f,Interpolation.sineIn),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            tahm.actorTahm().setVisible(true);
                            tahm.actorTahm().addAction(Actions.fadeIn(1f));
                            tahmbubble.setVisible(true);
                            tahmbubble.addAction(Actions.fadeIn(1f));
                            tahm.tahmBounds().setSize(tahm.getWidth(),tahm.getHeight());
                            bubbleBounds.setSize(tahmbubble.getWidth(),tahmbubble.getHeight());
                        }
                    })));
                }
            }
        }
    }

    public void mountainBody(){
        /*world = new World(new Vector2(WIDTH*2,HEIGHT*2),true);
        Body mountainModel;
        Vector2 bodypos;
        Vector2 bodyposOrigin;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(talonwall1.getX(),talonwall1.getY());
        BodyEditorLoader physicsBodies = new BodyEditorLoader(Gdx.files.internal("physics/talonwall"));
        FixtureDef fd = new FixtureDef();
        fd.density = 1;fd.friction = 0.5f;
        fd.restitution = 0.3f;
        mountainModel = world.createBody(bodyDef);
        //mountainModel.setUserData(talonwall1);

        physicsBodies.attachFixture(mountainModel,"talonwall",fd,400);
        bodyposOrigin = physicsBodies.getOrigin("talonwall",400).cpy();
        bodypos = mountainModel.getPosition().sub(bodyposOrigin);
        */
        talonwall1.setPosition(WIDTH*2-450,HEIGHT*2-300);
        talonwall1.setSize(400,400);
        talonwall2.setSize(400,400);
        talonwall2.setPosition(screenBounds.getX()+150,screenBounds.getY()+500);
        wallBounds = new Rectangle(talonwall2.getX()+125,talonwall2.getY(),talonwall2.getWidth()/3,talonwall2.getHeight());
        wallBounds2 = new Rectangle(talonwall1.getX()+75,talonwall1.getY()+75,talonwall1.getWidth()/1.5f,talonwall1.getHeight()/2);
        prisBounds = new Rectangle(runepris.getX(),runepris.getY(),runepris.getWidth(),runepris.getHeight());


    }

    public void resetOver(){
        overload.setPosition(WIDTH+20,HEIGHT,Align.center);
        overBounds.setPosition(WIDTH,HEIGHT);
        overload.setTouchable(Touchable.enabled);
        overcount=overcount+1;
        overload.clearActions();
        burst.setVisible(false);
    }

    public void fizzle(){

        overload.clearActions();
        tahm.actorTahm().clearActions();
        stage.addActor(tahmbreak);
        music.play();
        tahmbreak.setPosition(tahm.actorTahm().getX(),tahm.actorTahm().getY());
        tahmbreak.setSize(200,200);

        overload.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));
        stage.addAction(Actions.sequence(Actions.fadeOut(3f), Actions.removeActor()));
        tahm.actorTahm().addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));
        tahmbreak.addAction(Actions.sequence(Actions.delay(1.5f),Actions.hide(),Actions.removeActor()));

    }

    public void daggerSet(){
        talondags.setVisible(true);


        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                talondags.setVisible(false);
                talondags.setRotation(0);
            }
        },.5f);

    }

    public void ultSet(){
        talonult.setSize(800,800);
        talonult.setVisible(false);
        talonult.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.delay(6),Actions.run(new Runnable() {
            @Override
            public void run() {
                talonult.setVisible(true);
                usetalonult=true;
                talon.actorTalon().setVisible(false);
                talon.speedup(2f);
                //talonult.getAnimation().getKeyFrame(0);
            }
        }),Actions.delay(.5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                talonult.setVisible(false);
                usetalonult=false;
            }
        }),Actions.delay(2.5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                talon.actorTalon().setVisible(true);
                talon.slowdown(2f);
            }
        }))));
    }

    public void caught(){

        if(talon.isRaked() && !rakeonce){
            daggerSet();
            stage2.cancelTouchFocus(runepris);
            rakeonce=true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    rakeonce = false;
                }
            },4f);
        }

        if(talon.isJumped()){
            talon.actorTalon().clear();
            runepris.clear();
            if(!blooddrawn&&!jumped){
                setDeathColor(talon.actorTalon());
                setDeathColor(runepris);
                music2.play();
                blooddrawn=true;
                jumped=true;
            }
            //causes alpha to be constantly 1
            talon.actorTalon().addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            runepris.addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            //stage2.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));
            bg2.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));



            Timer.schedule(new Timer.Task(){
                @Override
                public void run(){
                    level2=false;
                    crystals2=crystals2+crystallize;
                    prefs.putLong("crystals2",crystals2);
                    prefs.flush();
                    stage2.clear();
                    music2.dispose();
                    music3.dispose();
                    stage2.dispose();
                    stage3.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(2f)));
                    level3=true;


                }
            },3f);
        }
    }

    public void tappedOut(){
        /*if(bar.getValue() == bar.getMaxValue()){
            barmax = true;
            System.out.println("Took "+ elapsedTime + " to complete.");

            */
        //score for 3rd level
        if(counter>=450f){
            barmax = true;
            music4.play();
            System.out.println("this is when it starts");
            spellflux.addAction(Actions.fadeOut(2f));
            overloadflux.addAction(Actions.fadeOut(2f));
            runeprisflux.addAction(Actions.fadeOut(2f));
            //bar.addAction(Actions.fadeOut(2f));
            ryze.addAction(Actions.fadeOut(2f));
            aura.addAction(Actions.fadeOut(2f));
            //loader.act(delta);
            loader.addAction(Actions.sequence(Actions.fadeOut(1f),Actions.hide(),Actions.removeActor()));
            //either stage or bg
            bg3.addAction(Actions.sequence(Actions.fadeOut(3f),Actions.hide(),Actions.removeActor()));
            if(elapsedTime<=12){
                if(!scorestop5) {
                    crystals3 = crystals3 + 100;
                    scorestop5 = false;
                    hit3=true;
                    prefs.putLong("crystals3",crystals3);
                    prefs.flush();
                }

            }
            else if(elapsedTime>=12){
                if(!scorestop6) {
                    crystals3 = crystals3 + 70;
                    scorestop6 = true;
                    prefs.putLong("crystals3",crystals3);
                    prefs.flush();
                }
            }
            else if(elapsedTime>=14){
                if(!scorestop7) {
                    crystals3 = crystals3 + 50;
                    scorestop7 = true;
                    prefs.putLong("crystals3",crystals3);
                    prefs.flush();
                }
            }
            else{
                if(!scorestop8) {
                    crystals3 = crystals3 + 20;
                    scorestop8 = true;
                    prefs.putLong("crystals3",crystals3);
                    prefs.flush();
                }
            }


            Timer.schedule(new Timer.Task(){
                @Override
                public void run(){
                    //if over doesnt hit the mark
                    level3=false;
                    stage3.clear();
                    stage3.dispose();
                    music4.dispose();
                    stage4.addAction(Actions.sequence(Actions.fadeIn(2f)));
                    level4=true;


                }
            },3f);
        }
    }
    public void runeRotate(){
        loader.addAction(Actions.rotateBy(180f,2f,Interpolation.swingOut));
    }
    public void runeImpact(){
        runeimpactact = Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.delay(5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                runeimpactstart=true;
            }
        }),Actions.moveTo(ryze.getX()+300,ryze.getY()+350,loaderspeed)));
        loader.addAction(runeimpactact);
    }
    public void runeTrap(){
        runetrapflux.setSize(loader.getWidth(),loader.getHeight());
        runetrapflux.setPosition(loader.getX()-400,loader.getY()-400);
        stage3.addActor(runetrapflux);
        loader.removeAction(runeimpactact);
        loaderspeed=loaderspeed-1f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runetrapflux.remove();
                runeImpact();
            }
        },2f);
    }

    public void ryzeEnter(){
        ryzecome=true;
        lightbeam.addAction(Actions.sequence(Actions.delay(.4f),Actions.run(new Runnable() {
            @Override
            public void run() {
                ryze.addAction(Actions.fadeIn(.7f));
                aura.addAction(Actions.fadeIn(.7f));
                //lightbeam.addAction(Actions.alpha(0));
            }
        })));
    }

    public void setDeathColor(Actor actor){
        actor.setColor(100,0,0,1);
    }

    public void progress(){

        //old progressbar scene2d method
        /*skin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(com.badlogic.gdx.graphics.Color.CYAN);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("loadbar.png"))));
        textureBar2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("runes/rune3half.png"))));
        textureBar.setMinWidth(500f);
        textureBar.setMinHeight(500f);
        textureBar2.setMinWidth(100f);
        textureBar2.setMinHeight(100f);
        barStyle = new ProgressBar.ProgressBarStyle(textureBar,textureBar2);
        //barStyle.background = barStyle.disabledBackground;
        barStyle.knobBefore = textureBar2;
        //barStyle.knob = textureBar2;
        bar = new ProgressBar(0, 100, 2f, true, barStyle);
        bar.setPosition(WIDTH/2,HEIGHT/2);
        bar.setValue(0f);
        bar.setSize(textureBar.getMinWidth(),textureBar.getMinHeight());
        //bar.setScale(5f);
        bar.setAnimateInterpolation(Interpolation.bounceIn);
        //bar.setSize(WIDTH,HEIGHT);
        bar.setAnimateDuration(2);
        */
        loader.setPosition(stage3.getWidth()/2-loader.getWidth(),stage3.getHeight()/2-loader.getHeight()-30);
        loader.setSize(400,400);
        loader.addAction(Actions.repeat(RepeatAction.FOREVER,Actions.sequence(Actions.parallel(Actions.moveBy(0,-25,1.3f),Actions.scaleBy(.1f,.1f,.3f)),Actions.parallel(Actions.moveBy(0,25,1.3f),Actions.scaleBy(-.1f,-.1f,.3f)))));

    }

    public void arrowReset(){
        ashearrow.clear();
        ashearrow.setPosition(screenBounds.getX()-200,screenBounds.getY());
    }

    public void fireArrow(){
        /*Rectangle arrow = new Rectangle();
        arrow.y =  MathUtils.random(screenBounds.getY(), screenBounds.getHeight());
        arrow.x = screenBounds.getX() - 200;
        arrow.setSize(ashearrow.getWidth(),ashearrow.getHeight());
        arrowBounds.add(arrow);
        lastArrowTime = TimeUtils.nanoTime();

        Iterator<Rectangle> iter = arrowBounds.iterator();
        while(iter.hasNext()) {
            Rectangle arrow2 = iter.next();
            arrow2.x += 100 * Gdx.graphics.getDeltaTime();
            if(arrow.getX() => screenBounds.getWidth()+200) iter.remove();
        }*/
        //one way to do arrow spawn
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                final float randomfire = MathUtils.random(screenBounds.getY(),screenBounds.getHeight());
                ashearrow.setPosition(screenBounds.getX(),randomfire);
                ashearrow.addAction(Actions.sequence(Actions.moveTo(screenBounds.getWidth()+200,randomfire,4f)));
            }
        },0f,5f);

    }

    public void changePlaces(){
        /*if(MathUtils.random(0,400)==25) {
            aurelion.addAction(Actions.fadeIn(1f));
            stage4.cancelTouchFocus();
            if(!bouncefinished && random == 1 && !inside1){
                talonwarp.actorTalon().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(2f),Actions.hide()), Actions.delay(1f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(!talon.actorTalon().isVisible()){
                            aureltouch1=false;
                        }
                        else if(!tahm.actorTahm().isVisible()){
                            aureltouch2=false;
                        }
                        else if(!ren.actorRen().isVisible()){
                            aureltouch3=false;
                        }
                        else if(!ann.actorAnn().isVisible()){
                            aureltouch4=false;
                        }
                    }
                }),Actions.parallel(Actions.fadeIn(1f), Actions.show(), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        aurelion.addAction(Actions.fadeOut(1f));
                    }
                }))));
                aureltouch1=true;
            }
            if(!bouncefinished2 && random == 2 && !inside2){
                tahmwarp.actorTahm().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(2f),Actions.hide()), Actions.delay(1f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(!talon.actorTalon().isVisible()){
                            aureltouch1=false;
                        }
                        else if(!tahm.actorTahm().isVisible()){
                            aureltouch2=false;
                        }
                        else if(!ren.actorRen().isVisible()){
                            aureltouch3=false;
                        }
                        else if(!ann.actorAnn().isVisible()){
                            aureltouch4=false;
                        }
                    }
                }),Actions.parallel(Actions.fadeIn(1f), Actions.show(), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        aurelion.addAction(Actions.fadeOut(1f));
                    }
                }))));
                aureltouch2=true;
            }
            if(!bouncefinished3 && random == 3 && !inside3){
                ren.actorRen().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(2f),Actions.hide()), Actions.delay(1f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(!talon.actorTalon().isVisible()){
                            aureltouch1=false;
                        }
                        else if(!tahm.actorTahm().isVisible()){
                            aureltouch2=false;
                        }
                        else if(!ren.actorRen().isVisible()){
                            aureltouch3=false;
                        }
                        else if(!ann.actorAnn().isVisible()){
                            aureltouch4=false;
                        }
                    }
                }),Actions.parallel(Actions.fadeIn(1f), Actions.show(), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        aurelion.addAction(Actions.fadeOut(1f));
                    }
                }))));
                aureltouch3=true;
            }
            if(!bouncefinished4 && random == 4 && !inside4){
                ann.actorAnn().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(2f),Actions.hide()), Actions.delay(1f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(!talon.actorTalon().isVisible()){
                            aureltouch1=false;
                        }
                        else if(!tahm.actorTahm().isVisible()){
                            aureltouch2=false;
                        }
                        else if(!ren.actorRen().isVisible()){
                            aureltouch3=false;
                        }
                        else if(!ann.actorAnn().isVisible()){
                            aureltouch4=false;
                        }
                    }
                }),Actions.parallel(Actions.fadeIn(1f), Actions.show(), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        aurelion.addAction(Actions.fadeOut(1f));
                    }
                }))));
                aureltouch4=true;
            }
        }*/

        if(MathUtils.random(0,4)==2) {
            aurelion.addAction(Actions.fadeIn(1f));
            stage4.cancelTouchFocus();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    aurelion.addAction(Actions.fadeOut(2f));
                }
            },1f);

            if(!bouncefinished && random == 1 && !inside1) {
                talonwarp.actorTalon().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(1f), Actions.hide()), Actions.delay(1f), Actions.parallel(Actions.fadeIn(1f), Actions.show())));
            }
                /*else if(bouncefinished && random == 1){
                    if(!bouncefinished2){
                        random=2;
                    }
                    else if(!bouncefinished3){
                        random=3;
                    }
                    else if(!bouncefinished4){
                        random=4;
                    }*/
        }
        if(!bouncefinished2 && random == 2 && !inside2){
            tahmwarp.actorTahm().addAction(Actions.sequence(Actions.moveBy(-200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(1f),Actions.hide()), Actions.delay(1f), Actions.parallel(Actions.fadeIn(1f), Actions.show())));
        }
                /*else if(bouncefinished2 && random == 2){
                    if(!bouncefinished){
                        random=1;
                    }
                    else if(!bouncefinished3){
                        random=3;
                    }
                    else if(!bouncefinished4){
                        random=4;
                    }
                }*/
        if(!bouncefinished3 && random == 3 && !inside3){
            ren.actorRen().addAction(Actions.sequence(Actions.moveBy(200, 25, 1f, Interpolation.bounceOut), Actions.parallel(Actions.fadeOut(1f),Actions.hide()), Actions.delay(1f), Actions.parallel(Actions.fadeIn(1f), Actions.show())));
        }
                /*else if(bouncefinished3 && random == 3){
                    if(!bouncefinished2){
                        random=2;
                    }
                    else if(!bouncefinished){
                        random=1;
                    }
                    else if(!bouncefinished4){
                        random=4;
                    }
                }*/
        if(!bouncefinished4 && random == 4 && !inside4){
            ann.actorAnn().addAction(Actions.sequence(Actions.moveBy(200, 25, 1f, Interpolation.bounceOut),Actions.parallel(Actions.fadeOut(1f),Actions.hide()), Actions.delay(1f), Actions.parallel(Actions.fadeIn(1f), Actions.show())));
        }
                /*else if(bouncefinished4 && random == 4){
                    if(!bouncefinished2){
                        random=2;
                    }
                    else if(!bouncefinished3){
                        random=3;
                    }
                    else if(!bouncefinished){
                        random=1;
                    }
                }*/
        //}


    }

    public void swapFlux(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                overloadflux.addAction(Actions.moveTo(runeprisflux.getX(),runeprisflux.getY(),1f,Interpolation.circleOut));
                runeprisflux.addAction(Actions.moveTo(overloadflux.getX(),overloadflux.getY(),1f,Interpolation.circleOut));
                spellflux.addAction(Actions.sequence(Actions.delay(1f),Actions.moveTo(overloadflux.getX(),overloadflux.getY(),1f,Interpolation.circleOut)));
                runeprisflux.addAction(Actions.sequence(Actions.delay(1f),Actions.moveTo(spellflux.getX(),spellflux.getY(),1f,Interpolation.circleOut)));
            }
        },0f,3f);

    }

    public void leapStrike(final Actor actor){

        final Action clawstrike=Actions.sequence(Actions.run(new Runnable() {
            @Override
            public void run() {
                claw.setPosition(rengar.getX(),rengar.getY());
                claw.addAction(Actions.fadeIn(.5f));
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        claw.addAction(Actions.fadeOut(.5f));
                    }
                },2f);
            }
        }));

        if(MathUtils.random(0,75)==25) {
            rengar.addAction(Actions.fadeIn(1f));
            rengar.addAction(Actions.sequence(Actions.delay(1f), Actions.moveTo(actor.getX(), actor.getY(), 1f, Interpolation.exp10),Actions.run(new Runnable() {
                @Override
                public void run() {
                    claw.addAction(clawstrike);
                }
            })));
            //if (actor.getX() < realmbounds.getX()) {

            //}
            /*else if((actor.getX() > realmbounds.getWidth())) {
                rengar.addAction(Actions.sequence(Actions.rotateTo(360f, 2f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        rengar.addAction(Actions.moveTo(screenBounds.getWidth() + 200, rengar.getY(), 2f));
                    }
                })));
            }*/
        }
    }

    public void bounceBack() {
        if (!bouncefinished) {
            if (talonwarp.talonBounds().overlaps(realmbounds)) {
                flingback = Actions.moveBy(-flingXTalon, -flingYTalon, 2);
                if(talontouch){
                    talonwarp.actorTalon().setPosition(realmbounds.getX()-30,realmbounds.getY()-30);
                }
                if (!talontouch) {
                    talonwarp.actorTalon().addAction(flingback);
                }

            }

            if(talonwarp.talonBounds().overlaps(realmbounds)){
                inside1=true;
            }
            else{
                inside1=false;
            }
        }
        if (bouncefinished){
            talonwarp.actorTalon().clear();
            talonwarp.actorTalon().setTouchable(Touchable.disabled);
            talonwarp.actorTalon().setPosition(q1.getWidth()-talonwarp.actorTalon().getWidth(),q1.getHeight()-talonwarp.actorTalon().getHeight());
            talonwarp.actorTalon().setColor(Color.GOLD);
            if(!scorestop1){
                music5.play();
                crystals4=crystals4+150;
                prefs.putLong("crystals4",crystals4);
                scorestop1=true;
            }
        }


        //-------------------------------------------------------------------------------------------
        if (!bouncefinished2) {
            if (tahmwarp.tahmBounds().overlaps(realmbounds)) {
                flingback = Actions.moveBy(-flingXTahm, -flingYTahm, 2);
                if (!tahmtouch) {
                    tahmwarp.actorTahm().addAction(flingback);
                }
                if(tahmtouch){
                    tahmwarp.actorTahm().setPosition(realmbounds.getX()-30,realmbounds.getHeight()+500);
                }
            }

            if(tahmwarp.tahmBounds().overlaps(realmbounds)){
                inside2=true;
            }
            else{
                inside2=false;
            }
        }
        if (bouncefinished2){
            tahmwarp.actorTahm().clear();
            tahmwarp.actorTahm().setTouchable(Touchable.disabled);
            tahmwarp.actorTahm().setPosition(q2.getWidth()-tahmwarp.actorTahm().getWidth(),q2.getY());
            tahmwarp.actorTahm().setColor(Color.GOLD);
            if(!scorestop2) {
                music5.play();
                crystals4 = crystals4 + 150;
                prefs.putLong("crystals4",crystals4);
                scorestop2 = true;
            }
        }
        //-----------------------------------------------------------------------------------------------

        if (!bouncefinished3) {
            if (ren.renBounds().overlaps(realmbounds)) {
                flingback = Actions.moveBy(-flingXRen, -flingYRen, 2);
                if (!rentouch) {
                    ren.actorRen().addAction(Actions.sequence(Actions.moveBy(-flingXRen, -flingYRen, 3)));
                }
                if(rentouch){
                    ren.actorRen().setPosition(realmbounds.getWidth()+30,realmbounds.getY()-30);
                }
            }

            if(ren.renBounds().overlaps(realmbounds)){
                inside3=true;
            }
            else{
                inside3=false;
            }
        }
        if (bouncefinished3){
            ren.actorRen().clear();
            ren.actorRen().setTouchable(Touchable.disabled);
            ren.actorRen().setPosition(q3.getX(),q3.getHeight()-ren.actorRen().getHeight());
            ren.actorRen().setColor(Color.GOLD);
            if(!scorestop3) {
                music5.play();
                crystals4 = crystals4 + 150;
                prefs.putLong("crystals4",crystals4);
                scorestop3 = true;
            }
        }
        //------------------------------------------------------------------------------------------------
        if(!bouncefinished4){
            if(ann.annBounds().overlaps(realmbounds)){
                flingback = Actions.moveBy(-flingXAnn, -flingYAnn, 2);
                if(!anntouch ) {
                    ann.actorAnn().addAction(Actions.sequence(Actions.moveBy(-flingXAnn, -flingYAnn,3)));
                }
                if(anntouch){
                    ann.actorAnn().setPosition(realmbounds.getWidth()+30,realmbounds.getHeight()+500);
                }
            }

            if(ann.annBounds().overlaps(realmbounds)){
                inside4=true;
            }
            else{
                inside4=false;
            }
        }
        if (bouncefinished4){
            ann.actorAnn().clear();
            ann.actorAnn().setTouchable(Touchable.disabled);
            ann.actorAnn().setPosition(q4.getX(),q4.getY());
            ann.actorAnn().setColor(Color.GOLD);
            if(!scorestop4) {
                music5.play();
                crystals4 = crystals4 + 150;
                prefs.putLong("crystals4",crystals4);
                scorestop4=true;
            }
        }

        if(talontouch){
            //if(inside1=false) {
            talonwarp.actorTalon().removeAction(flingforward);
            talonwarp.actorTalon().removeAction(flingback);
            //}
        }
        if(tahmtouch){
            //if(inside2=false) {
            tahmwarp.actorTahm().removeAction(flingforward);
            tahmwarp.actorTahm().removeAction(flingback);
            //}
        }
        if(rentouch){
            //if(inside3=false) {
            ren.actorRen().removeAction(flingforward);
            ren.actorRen().removeAction(flingback);
            //}
        }
        if(anntouch){
            //if(inside4=false) {
            ann.actorAnn().removeAction(flingforward);
            ann.actorAnn().removeAction(flingback);
            //}
        }
    }

    public void bouncefinish(){
        if(bouncefinished && bouncefinished2 && bouncefinished3 && bouncefinished4){
            //COMPLETE reveal score screen
            music5.dispose();
            if(elapsedTimeWarp<=12){
                hit4=true;
            }
            talonwarp.actorTalon().addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            tahmwarp.actorTahm().addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            ren.actorRen().addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            ann.actorAnn().addAction(Actions.sequence(Actions.fadeOut(1.5f),Actions.removeActor()));
            bg4.addAction(Actions.sequence(Actions.fadeOut(2f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    manager.set(new ScoreScrn(manager));
                }
            })));
            prefs.flush();
            System.out.println(prefs.getLong("crystals"));
            System.out.println(prefs.getLong("crystals2"));
            System.out.println(prefs.getLong("crystals3"));
            System.out.println(prefs.getLong("crystals4"));
            System.out.println(hit1+"" +hit2+""+hit3+""+hit4);
            if(hit1&&hit2&&hit3&&hit4){
                prefs.putBoolean("unlocksecret",true);
            }
        }
    }


}
