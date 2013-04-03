import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		com.netent.jdbcdriver.datasource.DataSource ds_test = new com.netent.jdbcdriver.datasource.DataSource();
		System.out.println("Data source is :"+ds_test);
		System.out.println("Inside do get method");
		DataSource ds = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			ds = SqlUtil.getDataSource();
			conn = ds.getConnection();
			statement = conn.createStatement();
			statement.execute("select case when g.SeamlessWalletMode=1 then (isnull(REPLACE(g.FullName,'&euro;', '&#x20AC;'), g.GameId))+N' SW' else  (isnull(REPLACE(FullName,'&euro;', '&#x20AC;'), g.GameId))  end  as 'Game name',  gr.id as 'Gameround id',  gr.startdate as 'Start date',  p.username as 'User name',  gr.userId as 'User id',  amt.description as 'Money type',  coalesce(gr.casinoCurrencyBet, 0) as Bet,  coalesce((dbo.GetExchangeRate(gr.playerCurrencyId,gr.StartDate)*gr.playerCurrencyBonusBet), 0) as BonusBet,  coalesce(gr.casinoCurrencyWin, 0) as Win,  coalesce((dbo.GetExchangeRate(gr.playerCurrencyId,gr.StartDate)*gr.playerCurrencyBonusWin), 0) as BonusWin,  coalesce((dbo.GetExchangeRate(gr.playerCurrencyId,gr.StartDate)*gr.playerCurrencyNonWithdrawableBet), 0) as NonWithdrawableBet from GameRounds gr with (nolock)  inner join games g with (nolock) on gr.gameid = g.gameid inner join accountmoneytype amt with (nolock) on gr.moneytype=amt.id inner join player p with (nolock) on p.userid = gr.userid where gr.endDate is null and gr.startdate < '2013-03-29 16:45' order by gr.startdate desc");
			rs = statement.getResultSet();
			int columnCount = rs.getMetaData().getColumnCount();
			//System.out.println("column label "+rs.getMetaData().getColumnLabel(0));
			System.out.println("columncount is "+columnCount);
			while (rs.next()) {
                Object[] arr = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    Object column = rs.getObject(i + 1);
                    arr[i] = column;
                    //System.out.println(column);
                }
            }
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
