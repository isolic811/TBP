<?php

namespace AppBundle\Controller;

use AppBundle\Entity\RangList;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Ranglist controller.
 *
 * @Route("admin/ranglist")
 */
class RangListController extends Controller
{
    /**
     * Lists all rangList entities.
     *
     * @Route("/", name="admin_ranglist_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $rangLists = $em->getRepository('AppBundle:RangList')->findAll();

        return $this->render('ranglist/index.html.twig', array(
            'rangLists' => $rangLists,
        ));
    }

    /**
     * Creates a new rangList entity.
     *
     * @Route("/new", name="admin_ranglist_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $rangList = new Ranglist();
        $form = $this->createForm('AppBundle\Form\RangListType', $rangList);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($rangList);
            $em->flush();

            return $this->redirectToRoute('admin_ranglist_show', array('id' => $rangList->getId()));
        }

        return $this->render('ranglist/new.html.twig', array(
            'rangList' => $rangList,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a rangList entity.
     *
     * @Route("/{id}", name="admin_ranglist_show")
     * @Method("GET")
     */
    public function showAction(RangList $rangList)
    {
        $deleteForm = $this->createDeleteForm($rangList);

        return $this->render('ranglist/show.html.twig', array(
            'rangList' => $rangList,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing rangList entity.
     *
     * @Route("/{id}/edit", name="admin_ranglist_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, RangList $rangList)
    {
        $deleteForm = $this->createDeleteForm($rangList);
        $editForm = $this->createForm('AppBundle\Form\RangListType', $rangList);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('admin_ranglist_edit', array('id' => $rangList->getId()));
        }

        return $this->render('ranglist/edit.html.twig', array(
            'rangList' => $rangList,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a rangList entity.
     *
     * @Route("/{id}", name="admin_ranglist_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, RangList $rangList)
    {
        $form = $this->createDeleteForm($rangList);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($rangList);
            $em->flush();
        }

        return $this->redirectToRoute('admin_ranglist_index');
    }

    /**
     * Creates a form to delete a rangList entity.
     *
     * @param RangList $rangList The rangList entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(RangList $rangList)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('admin_ranglist_delete', array('id' => $rangList->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
