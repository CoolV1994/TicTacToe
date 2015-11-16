<?php
require_once 'config.php';

/**
 *
 * @author Vinnie
 */

// 5 minutes ago
$date_past = date('Y-m-d H:i:s', time() - 5 * 60);

try {
    $conn = new PDO('mysql:host='.DB_HOST.';dbname='.DB_NAME, DB_USER, DB_PASS);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$sql = "SELECT id, name FROM servers WHERE date >= :date ORDER BY date DESC";
    $stmt = $conn->prepare($sql);
	$stmt->bindValue(':date', $date_past, PDO::PARAM_STR);
    $stmt->execute();
	$stmt->setFetchMode(PDO::FETCH_OBJ);
	while ($server = $stmt->fetch()) {
		echo $server->id . " - " . $server->name . "\n";
	}
	if ($stmt->rowCount() == 0) {
		echo "No servers currently online.";
	}
} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}
