<?php
namespace AppBundle\Controller;

use Symfony\Component\Validator\Constraints\DateTime;
use AppBundle\Entity;
use AppBundle\Entity\RangList;

use FOS\RestBundle\Controller\FOSRestController;
use FOS\RestBundle\Controller\Annotations\Route;
use Symfony\Component\HttpFoundation\Response;
use FOS\RestBundle\View\View;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\Mapping as ORM;

class RestController extends FOSRestController
{
	/**
     * @Route("/api/questions")
     */
    public function getQuestions()
    {
        $result = $this->getDoctrine()->getRepository('AppBundle:Question')->findAll();
        if ($result === null || count($result) === 0) {
            return new View("No questions found!", Response::HTTP_NOT_FOUND);
        }
        return $result;
    }

	  /**
	 * @Route("api/rangList/")
	 */
	 public function postAction(Request $request)
	 {
	   $data = new RangList;
	   $username = $request->get('username');
	   $points = $request->get('points');
		 if(empty($username) || empty($points))
		 {
		   return new View("NULL VALUES ARE NOT ALLOWED", Response::HTTP_NOT_ACCEPTABLE); 
		 } 
		  $data->setUsername($username);
		  $data->setPoints($points);
		      
		  $data->setTimeUpdated(new \DateTime("now"));
		  $em = $this->getDoctrine()->getManager();
		  $em->persist($data);
		  $em->flush();
		  return new View("Result added successfully", Response::HTTP_OK);
	 }

	 /**
     * @Route("/api/topTen")
     */
    public function getTopTen()
    {
    	$em = $this->getDoctrine()->getEntityManager();
		$query = $em->createQuery(
		'SELECT c FROM AppBundle:RangList c ORDER BY c.points DESC'
		);
		$result = $query->setMaxResults(20)->getResult();
        if ($result === null || count($result) === 0) {
            return new View("No questions found!", Response::HTTP_NOT_FOUND);
        }
        return $result;
    }

}