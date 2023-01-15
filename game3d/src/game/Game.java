import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Game extends JFrame {
    private Transform3D rotate1 = new Transform3D();
    private Transform3D rotate2 = new Transform3D();

    public Game() {
        super("Spinning3DWorld");
        Canvas3D canvas3D = createCanvas3D();
        BranchGroup scene = createSceneGraph();
        connect(canvas3D, scene);
    }

    private Canvas3D createCanvas3D() {
        setSize(300, 300);
        getContentPane().setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        setSize(300, 300);
        getContentPane().add(canvas3D);
        return canvas3D;
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        TransformGroup spinner = new TransformGroup();
        spinner.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRoot.addChild(spinner);
        spinner.addChild(new ColorCube(0.3));
        spinner.addChild(makeSpin(spinner));
        return objRoot;
    }

    private RotationInterpolator makeSpin(
            TransformGroup spinner) {
        RotationInterpolator rotator = new RotationInterpolator(new Alpha(-1, 3000), spinner);
        rotator.setTransformAxis(rotateCube());
        BoundingSphere bounds = new BoundingSphere();
        rotator.setSchedulingBounds(bounds);
        return rotator;
    }

    private Transform3D rotateCube() {
        rotate1.rotX(Math.PI / 4.0d);
        rotate2.rotY(Math.PI / 3.0d);
        rotate1.mul(rotate2);
        return rotate1;
    }

    private void connect(Canvas3D canvas3D, BranchGroup scene) {
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }

    public static void main(String[] args) {
        new Game().setVisible(true);
    }
}