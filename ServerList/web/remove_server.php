<?php
require_once 'config.php';

/**
 *
 * @author Vinnie
 */
if (!$_POST['port']) {
	die();
}

try {
    $conn = new PDO('mysql:host='.DB_HOST.';dbname='.DB_NAME, DB_USER, DB_PASS);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = "UPDATE servers SET date=null WHERE ip=:ip AND port=:port";
	$stmt = $conn->prepare($sql);
	$stmt->bindValue(':ip', $_SERVER['REMOTE_ADDR'], PDO::PARAM_STR);
	$stmt->bindValue(':port', $_POST['port'], PDO::PARAM_INT);
	$stmt->execute();
	echo "done";
} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}
