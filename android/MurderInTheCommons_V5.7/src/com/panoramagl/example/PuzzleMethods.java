//this class created by Murder in the Commons for specialized program
package com.panoramagl.example;

import android.content.Context;
import android.content.Intent;

public class PuzzleMethods {
	public static void TestActivity(Context context)
	{
		Intent intent = new Intent(context, TestActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void PhoneBookMain(Context context)
	{
		Intent intent = new Intent(context, PhoneBookMain.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void ShiftingMap(Context context)
	{
		Intent intent = new Intent(context, ShiftingMap.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void CrumpledMap(Context context)
	{
		Intent intent = new Intent(context, CrumpledMap.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void Map(Context context)
	{
		Intent intent = new Intent(context, Map.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void Envelope(Context context)
	{
		Intent intent = new Intent(context, Envelope.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void GhostLeg(Context context)
	{
		Intent intent = new Intent(context, GhostLeg.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void ExitScreen(Context context)
	{
		Intent intent = new Intent(context, ExitScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void Notes(Context context)
	{
		Intent intent = new Intent(context, Notes.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	public static void Credits(Context context)
	{
		Intent intent = new Intent(context, Credits.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
