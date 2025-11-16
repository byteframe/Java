#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

GLuint listID;

// these are callback functions that register with glut
void mainMenuCB(int value)
{
    if (value == 99) {
        exit(0);
    }
}

// todo: describe this function
void display()
{
    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    glTranslatef(0.f, 0.f, -4.f);
    glCallList(listID);
    glutSwapBuffers();
}

// called when the window size changes
void reshape(int w, int h)
{
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(50., (double)w/(double)h, 1., 10.);
    glMatrixMode(GL_MODELVIEW);
}

// standard main function signature, char** is ....
int main (int argc, char** argv)
{
    // init and create double buffered window via glut
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE);
    glutInitWindowSize(512, 384);
    glutCreateWindow("timGlut");
    
    // check for opengl version 1.4
    char* versionString = (char*)glGetString(GL_VERSION);
    printf("OpenGL Version String : %s \n", versionString);
    if (strncmp(versionString, "1.4", 3) > -1) {
        printf("Your OpenGL implementation is too old!\n");
        printf("Terminating!\n");
        exit(1);
    }
    glDisable(GL_DITHER);
    glEnableClientState(GL_VERTEX_ARRAY);

    //
    const GLfloat data[] = {
      -1.f, -1.f, 0.f,
      1.f, -1.f, 0.f,
      0.f, 1.f, 0.f };

    glVertexPointer(3, GL_FLOAT, 0, data);

    listID = glGenLists(1);
    glNewList(listID, GL_COMPILE);
    glDrawArrays(GL_TRIANGLES, 0, 3);

    glEndList();

    // register callback functions.
    glutDisplayFunc(display);
    glutReshapeFunc(reshape);

    // glut menu WHY INT?
    int mainMenu = glutCreateMenu(mainMenuCB);
    glutAddMenuEntry("Quit", 99);
    glutAttachMenu(GLUT_RIGHT_BUTTON);
    
    // stars main loop (blocks) that handles events and such.
    glutMainLoop();

    // called after glutMainLoop returns.
    return 0;
}
    
